package com.hospital.service;

import com.hospital.model.Reminder;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface ReminderService {
    List<Reminder> findAllByDoctorId(Long doctorId);
    List<Reminder> findAllByPatientId(Long patientId);

    Long countByPatientIdAndLevelAndStatus(Long patientId, String level, Byte status);

    void save(Reminder reminder);

    void updateStatusById(Long id);

    List<Reminder> getHistory(Long patientId, Date pastDate1, Date pastDate2, Date currentTime, Byte status);
}
