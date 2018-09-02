package com.hospital.controller;

import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.model.User;
import com.hospital.service.DoctorService;
import com.hospital.service.PatientService;
import com.hospital.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/doctor", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('DOCTOR')")
    public String doctorLogin(@RequestParam String username, @RequestParam String password) throws ServletException {
        return doLogin(username, password);
    }

    @RequestMapping(value="/patient", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('PATIENT')")
    public String patientLogin(@RequestParam String username, @RequestParam String password) throws ServletException{
        return doLogin(username, password);
    }

    private String doLogin(String username, String password) throws ServletException{

        if (username == null || username.equals("") || password == null) {
            throw new ServletException("Please fill in username and password");
        }
        User user = userService.findByUsername(username);
        if (user == null)
            throw new ServletException("User email not found.");
        String pwd = user.getPassword();
        if (!password.equals(pwd))
            throw new ServletException("Invalid login. Please check your name and password.");

        return "Success!";
    }
}
