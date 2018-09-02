import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import {UserService} from '../services/user.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {StompService} from '@stomp/ng2-stompjs'
import {Message} from '@stomp/stompjs';

import $ from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  username = localStorage.getItem("username");
  private subscription: Subscription;
  public subscribed: boolean = false;
  public messages: Observable<Message>;

  constructor(private router: Router, private userService: UserService, private http: HttpClient, 
              private stompService: StompService) { }

  ngOnInit() {
    this.getDoctorInfo(this.username).subscribe();
    // this.subscribed = false;
    this.subscribe();
  }

  public subscribe() {
    if (this.subscribed) {
      return;
    }
    let doctorId = localStorage.getItem("doctorId");
    this.messages = this.stompService.subscribe('/updateStatus/'+doctorId);
    this.subscription = this.messages.map((message: Message) => {
        return message.body;
      }).subscribe((msg_body: string) => {
        if($('#updateDOM').children().length==0){
          $("#updateDOM").append("<div class='message' id='updateData'>"+msg_body+"</div>");
          console.log(`Received: ${msg_body}` + 'from home!');
        }
      });
    this.subscribed = true;
  }

  public unsubscribe() {
    if (!this.subscribed) {
      return;
    }
    this.stompService.disconnect();
    this.subscription.unsubscribe();
    this.subscription = null;
    this.messages = null;
    this.subscribed = false;
  }

  logout() {
    $("#updateDate").remove();
    this.unsubscribe();
    this.userService.logout();
    this.router.navigate(['/login']);
  }

  private getDoctorInfo(username: string){
    const URL = 'http://localhost:8080';
    const token = localStorage.getItem("access_token");
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + token
      })
    };
    
    return this.http.get(URL+'/get/user/doctor/'+username, httpOptions)
      .map((res: any) => {
        if (res.id != null) {
          localStorage.setItem("doctorId", res.id);
          return res.id;
        }
        return null;
      });
  }

}
