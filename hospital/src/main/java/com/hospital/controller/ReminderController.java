package com.hospital.controller;

import com.hospital.model.*;
import com.hospital.service.PatientService;
import com.hospital.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

@RestController
public class ReminderController {

    @Autowired
    private ReminderService reminderService;
    @Autowired
     private PatientService patientService;

    @RequestMapping(value = "/post/reminder", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('DOCTOR')")
    public void addReminder(@RequestParam String name, @RequestParam String description,
                            @RequestParam String createtime, @RequestParam String duetime,
                            @RequestParam String level, @RequestParam String doctorId,
                            @RequestParam String patientId) throws ParseException {
        Byte status=0;
        Date creationTime=new Date(Long.parseLong(createtime));
//        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd|hh:mm");
//
//        java.util.Date date=formatter.parse(duetime);
//        Date newDueTime=new Date(date.getTime());
        Date newDueTime = new Date(Long.parseLong(duetime));
        level=level.trim();

        Patient patient=patientService.findById(Long.parseLong(patientId));
        String patientName=patient.getFirstName() + " " + patient.getLastName();
        Reminder reminder = new Reminder(name, description, creationTime, newDueTime, level,
                status, Long.parseLong(patientId), patientName, Long.parseLong(doctorId));

        reminderService.save(reminder);
    }

    @RequestMapping(value = "/get/reminder/doctor/{doctorId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<List<ReminderSentToDoctor>> getDoctorReminder(@PathVariable("doctorId") String doctorId){
        List<Reminder> reminders=reminderService.findAllByDoctorId(Long.parseLong(doctorId));
        Map<Long, Long> mapHigh=new HashMap<>();
        Map<Long, Long> mapMid=new HashMap<>();
        Map<Long, Long> mapLow=new HashMap<>();
        Map<Long, String> patients=new HashMap<>();
        Byte status=0;
        for(Reminder reminder : reminders){
            Long patientId=reminder.getPatientId();
            if(patients.get(patientId)==null){
                String patientName=reminder.getPatientName();
                patients.put(patientId, patientName);
            }
            if(mapHigh.get(patientId)==null){
                Long highCount = reminderService.countByPatientIdAndLevelAndStatus(patientId,"high", status);
                mapHigh.put(patientId,highCount);
            }
            if(mapMid.get(patientId)==null){
                Long midCount = reminderService.countByPatientIdAndLevelAndStatus(patientId,"middle", status);
                mapMid.put(patientId,midCount);
            }
            if(mapLow.get(patientId)==null){
                Long lowCount = reminderService.countByPatientIdAndLevelAndStatus(patientId,"low", status);
                mapLow.put(patientId,lowCount);
            }
        }

        List<ReminderSentToDoctor> response = new LinkedList<>();
        for(Map.Entry<Long,String> entry:patients.entrySet()){
            Long patientId=entry.getKey();
            String patientName=entry.getValue();
            Long highCount=mapHigh.get(patientId);
            Long midCount=mapMid.get(patientId);
            Long lowCount=mapLow.get(patientId);
            response.add(new ReminderSentToDoctor(patientId,patientName,highCount,midCount,lowCount));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/history/{patientId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<List<ReminderHistory>> getReminderHistory(@PathVariable("patientId") String patientId){
        Byte status=0;
        long millis=System.currentTimeMillis();
        Date today = new Date(millis);
        List<Reminder> reminders;
        List<ReminderHistory> response=new LinkedList<>();
        int expiredCount=0;
        Patient patient=patientService.findById(Long.parseLong(patientId));
        String patientName=patient.getFirstName()+" "+patient.getLastName();
        for(int pastday=-7;pastday<0;pastday++){
            Date pastDate1 = this.addDays(today, pastday);
            Date pastDate2= this.addDays(today, pastday+1);
            reminders=reminderService.getHistory(Long.parseLong(patientId),pastDate1,pastDate2,today,status);
            expiredCount=reminders.size();
            int day=pastday+8;
            response.add(new ReminderHistory(patientName,day,expiredCount));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/reminder/patient/{patientId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<List<Reminder>> getPatientReminder(@PathVariable("patientId") String patientId){
        List<Reminder> reminders=reminderService.findAllByPatientId(Long.parseLong(patientId));
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }

    @RequestMapping(value = "/put/reminder/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('PATIENT')")
    public HttpStatus updateStatus(@PathVariable("id") String id){
        this.reminderService.updateStatusById(Long.parseLong(id));
        return HttpStatus.OK;
    }


    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }
}
