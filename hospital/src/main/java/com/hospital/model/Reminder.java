package com.hospital.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "reminder")
public class Reminder {

    private Long id;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "YYYY-MM-dd hh:mm:ss")
    private Date createtime;
    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "YYYY-MM-dd hh:mm:ss")
    private Date duetime;
    private String level;
    private Byte status;
    private Long patientId;
    private String patientName;
    private Long doctorId;


    public Reminder(){}

    public Reminder(String name, String description, Date createtime, Date duetime, String level,
                    Byte status, Long patientId, String patientName, Long doctorId) {
        this.name = name;
        this.description = description;
        this.createtime = createtime;
        this.duetime = duetime;
        this.level = level;
        this.status = status;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorId = doctorId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Date getCreatetime() { return createtime; }

    public void setCreatetime(Date createtime) { this.createtime = createtime; }

    public Date getDuetime() { return duetime; }

    public void setDuetime(Date duetime) { this.duetime = duetime; }

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }

    public Byte getStatus() { return status; }

    public void setStatus(Byte status) { this.status = status; }

    public Long getPatientId() { return patientId; }

    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }

    public void setPatientName(String patientName) { this.patientName = patientName; }

    public Long getDoctorId() { return doctorId; }

    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

}
