package com.hospital.service;

import com.hospital.model.Doctor;

public interface DoctorService {
    Doctor findByUserId(Long userId);
}
