package com.hospital.model;

public class ReminderHistory {

    private String patientName;
    private int day;
    private int expiredCount;

    public ReminderHistory(){}

    public ReminderHistory(String patientName, int day, int expiredCount) {
        this.patientName = patientName;
        this.day = day;
        this.expiredCount = expiredCount;
    }

    public String getPatientName() { return patientName; }

    public void setPatientName(String patientName) { this.patientName = patientName; }

    public int getDay() { return day; }

    public void setDay(int day) { this.day = day; }

    public int getExpiredCount() { return expiredCount; }

    public void setExpiredCount(int expiredCount) { this.expiredCount = expiredCount; }
}
