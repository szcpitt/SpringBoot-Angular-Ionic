package com.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/send/newReminder")
    public void onReceivedReminder(String patientId){
        messagingTemplate.convertAndSend("/updateReminder/" + patientId, "You have a new reminder.");
    }

    @MessageMapping("/send/newStatus")
    public void onReceivedStatus(String doctorIdAndPatientId){
        String doctorId=doctorIdAndPatientId.split(":")[0];
        String patientId=doctorIdAndPatientId.split(":")[1];
        messagingTemplate.convertAndSend("/updateStatus/" + doctorId, "Patient "+ patientId + " finished a task.");
    }

    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        messagingTemplate.convertAndSend("/errors", exception.getMessage());
        return exception.getMessage();
    }
}

