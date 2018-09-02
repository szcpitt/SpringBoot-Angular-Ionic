package com.hospital.service;

import com.hospital.model.Reminder;
import com.hospital.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    @Override
    public List<Reminder> findAllByDoctorId(Long doctorId){
        return reminderRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public List<Reminder> findAllByPatientId(Long patientId){
        return reminderRepository.findAllByPatientId(patientId);
    }

    @Override
    public List<Reminder> getHistory(Long patientId, Date pastDate1, Date pastDate2, Date currentTime, Byte status){
        return reminderRepository.findAllByPatientIdAndCreatetimeBetweenAndDuetimeBeforeAndStatus(
                patientId,pastDate1,pastDate2,currentTime,status);
    }

    @Override
    public Long countByPatientIdAndLevelAndStatus(Long patientId, String level, Byte status){
        return reminderRepository.countByPatientIdAndLevelAndStatus(patientId, level, status);
    }

    @Override
    public void updateStatusById(Long id){
        Byte status=1;
        reminderRepository.updateStatusById(status,id);
    }

    @Override
    public void save(Reminder reminder){
        reminderRepository.save(reminder);
    }
}
