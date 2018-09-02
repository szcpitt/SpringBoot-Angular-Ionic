import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Reminder } from '../models/reminder.model';
import { ReminderHistory } from '../models/reminderHistory.model';

@Injectable()
export class ReminderService {

  static rootURL = "http://localhost:8080";

  reminder : Reminder[] = [];

  pID:string="";

  constructor(private http: HttpClient) { }

  add(name: string, description: string, createtime: string, duetime: string, 
      level: string, patientId: number){
    
    const doctorId = localStorage.getItem("doctorId");
    // const duetime = date.toLocaleString() + "|" + time;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/x-www-form-urlencoded',
        'Authorization': 'Bearer ' + localStorage.getItem("access_token")
      })
    };
    let body = `name=${name}&description=${description}&createtime=${createtime}&level=${level}
    &doctorId=${doctorId}&patientId=${String(patientId)}&duetime=${duetime}`;
    body=body.replace(/(\r\n\t|\n|\r\t)/gm,"");
    
    this.http.post(ReminderService.rootURL+"/post/reminder", body, httpOptions).subscribe();
    console.log("Finished post!");
  }

  getReminders() {
    const token = localStorage.getItem("access_token");
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      })
    };
    const doctorId = localStorage.getItem("doctorId");
    const url = ReminderService.rootURL+'/get/reminder/doctor/'+doctorId;
    return this.http.get<Reminder[]>(url,httpOptions);      
  }

  getReminderHistory(): Observable<ReminderHistory[]>{
    const token = localStorage.getItem("access_token");
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      })
    };
    const url = ReminderService.rootURL+'/get/history/'+this.pID;
    return this.http.get<ReminderHistory[]>(url,httpOptions);
  }

  getPatientId(patientId:string){
    this.pID=patientId;
  }

}
