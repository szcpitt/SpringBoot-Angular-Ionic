package com.hospital.repository;

import com.hospital.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findAllByDoctorId(Long doctorId);
    List<Reminder> findAllByPatientId(Long patientId);

    Long countByPatientIdAndLevelAndStatus(Long patientId, String level, Byte status);

    @Modifying
    @Transactional
    @Query("update Reminder r set r.status = :status where r.id = :id")
    void updateStatusById(@Param("status") Byte status, @Param("id") Long id);

    List<Reminder> findAllByPatientIdAndCreatetimeBetweenAndDuetimeBeforeAndStatus(Long patientId, Date pastDate1,
                                                                                   Date pastDate2, Date currentTime, Byte status);
}
