import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import {UserService} from '../services/user.service';
import {ReminderService} from '../services/reminder.service';

import {StompService} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';

import $ from 'jquery';

@Component({
  selector: 'app-add-reminder',
  templateUrl: './add-reminder.component.html',
  styleUrls: ['./add-reminder.component.css']
})
export class AddReminderComponent implements OnInit {

  model: any = {};
  loading = false;
  error = '';
  private subscription: Subscription;
  public subscribed: boolean;
  public messages: Observable<Message>;

  constructor(private userService: UserService, private router: Router, 
              private reminderService: ReminderService, private stompService: StompService) { }

  ngOnInit() {
    // this.subscribed = false;
    // this.subscribe();
  }

  // public subscribe() {
  //   if (this.subscribed) {
  //     return;
  //   }
  //   let doctorId = localStorage.getItem("doctorId");
  //   this.messages = this.stompService.subscribe('/updateStatus/'+doctorId);
  //   this.subscription = this.messages.map((message: Message) => {
  //       return message.body;
  //     }).subscribe((msg_body: string) => {
  //       $("#updateDOM").append("<div class='message' id='updateData'>"+msg_body+"</div>");
  //       console.log(`Received: ${msg_body}` + 'from reminder table!');
  //     });
  //   this.subscribed = true;
  // }

  // public unsubscribe() {
  //   if (!this.subscribed) {
  //     return;
  //   }
  //   this.stompService.disconnect();
  //   this.subscription.unsubscribe();
  //   this.subscription = null;
  //   this.messages = null;
  //   this.subscribed = false;
  // }

  logout() {
    $("#updateData").remove();
    // this.unsubscribe();
    this.userService.logout();
    this.router.navigate(['/login']);
  }

  add() {
    this.loading = true;
    let createtime = String(Date.now());
    let duetime = String(this.model.hours*1000*60*60 + Number(createtime));
    this.reminderService.add(this.model.name, this.model.description, createtime, duetime,
       this.model.level, this.model.patientId);

    let message=this.model.patientId;
    this.stompService.publish("/app/send/newReminder", message, {});
    this.router.navigate(['home']);
  }

}
