package com.hospital.service;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient findById(Long id){
        return patientRepository.findById(id);
    }

    @Override
    public Patient findByUserId(Long userId){ return patientRepository.findByUserId(userId);}
}
