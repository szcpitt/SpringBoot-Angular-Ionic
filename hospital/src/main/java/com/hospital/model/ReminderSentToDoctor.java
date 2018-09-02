package com.hospital.model;

public class ReminderSentToDoctor {

    private  Long patientId;
    private String patientName;
    private Long highCount;
    private Long midCount;
    private Long lowCount;

    public ReminderSentToDoctor(){}

    public ReminderSentToDoctor(Long patientId, String patientName, Long highCount, Long midCount, Long lowCount) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.highCount = highCount;
        this.midCount = midCount;
        this.lowCount = lowCount;
    }

    public String getPatientName() { return patientName; }

    public Long getPatientId() { return patientId; }

    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public void setPatientName(String patientName) { this.patientName = patientName; }

    public Long getHighCount() { return highCount; }

    public void setHighCount(Long highCount) { this.highCount = highCount; }

    public Long getMidCount() { return midCount; }

    public void setMidCount(Long midCount) { this.midCount = midCount; }

    public Long getLowCount() { return lowCount; }

    public void setLowCount(Long lowCount) { this.lowCount = lowCount; }
}
