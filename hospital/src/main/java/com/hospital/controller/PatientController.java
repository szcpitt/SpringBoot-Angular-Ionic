package com.hospital.controller;

import com.hospital.model.Patient;
import com.hospital.model.User;
import com.hospital.service.PatientService;
import com.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get/user/patient/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<Patient> getDoctorInfo(@PathVariable("username") String username){
        User user=userService.findByUsername(username);
        Patient patient=patientService.findByUserId(user.getId());
        ResponseEntity<Patient> response = new ResponseEntity<>(patient, HttpStatus.OK);
        return response;
    }

}
