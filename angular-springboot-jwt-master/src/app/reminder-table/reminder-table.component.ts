import { Component, OnInit, ViewChild } from '@angular/core';
import { ReminderService } from '../services/reminder.service';
import {Reminder} from '../models/reminder.model';
import {DataSource} from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import {Router} from '@angular/router';
import {MatSort, MatTableDataSource} from '@angular/material'

@Component({
  selector: 'app-reminder-table',
  templateUrl: './reminder-table.component.html',
  styleUrls: ['./reminder-table.component.css']
})
export class ReminderTableComponent implements OnInit {

  dataSource = new MatTableDataSource();
  tmp=this.reminderService.getReminders().subscribe(reminder=>this.dataSource.data=reminder);
  
  // dataSource = new ReminderDataSource(this.reminderService);
  displayedColumns = ['patientName','highCount','midCount','lowCount'];

  constructor(private router: Router, private reminderService: ReminderService) { }
  
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {
    this.dataSource.sort = this.sort;
  }

  selectRow(patient:any){
    this.reminderService.getPatientId(patient.patientId);
    this.router.navigateByUrl("/history");
  }

}

// export class ReminderDataSource extends DataSource<any> {
//   constructor(private reminderService: ReminderService) {
//     super();
//   }
//   connect(): Observable<Reminder[]> {
//     return this.reminderService.getReminders();
//   }
//   disconnect() {}
// }


