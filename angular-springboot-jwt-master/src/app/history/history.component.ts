import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DataSource} from '@angular/cdk/collections';
import {UserService} from '../services/user.service';
import { Observable } from 'rxjs/Observable';
import { ReminderService } from '../services/reminder.service';
import {ReminderHistory} from '../models/reminderHistory.model'

@Component({
  selector: 'app-user',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  patiendId : string="";

  dataSource = new ReminderDataSource(this.reminderService);
  displayedColumns = ['patientName','day','expiredCount'];
  
  constructor(private router: Router, private userService: UserService, private reminderService: ReminderService) {
  }

  ngOnInit() {
    
  }

  logout() {
    this.userService.logout();
    this.router.navigate(['/login']);
  }
}

export class ReminderDataSource extends DataSource<any> {
  constructor(private reminderService: ReminderService) {
    super();
  }
  connect(): Observable<ReminderHistory[]> {
    return this.reminderService.getReminderHistory();
  }
  disconnect() {}
}
