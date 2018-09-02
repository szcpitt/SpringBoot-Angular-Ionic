package com.hospital.service;

import com.hospital.model.Patient;

public interface PatientService {
    Patient findById(Long patientId);

    Patient findByUserId(Long userId);
}
