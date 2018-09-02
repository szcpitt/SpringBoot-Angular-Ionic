package com.hospital.controller;

import com.hospital.model.Doctor;
import com.hospital.model.User;
import com.hospital.service.DoctorService;
import com.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get/user/doctor/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<Doctor> getDoctorInfo(@PathVariable("username") String username){
        User user=userService.findByUsername(username);
        Doctor doctor=doctorService.findByUserId(user.getId());
        ResponseEntity<Doctor> response = new ResponseEntity<>(doctor, HttpStatus.OK);
        return response;
    }
}
