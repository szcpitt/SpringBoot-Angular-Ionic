package com.hospital.model;

public class ReminderSentToPatient {

    private Long id;
    private String name;
    private String description;
    private String createtime;
    private String duetime;
    private String level;
    private Byte status;

    public ReminderSentToPatient(){}

    public ReminderSentToPatient(Long id, String name, String description, String createtime, String duetime, String level, Byte status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createtime = createtime;
        this.duetime = duetime;
        this.level = level;
        this.status = status;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getCreatetime() { return createtime; }

    public void setCreatetime(String createtime) { this.createtime = createtime; }

    public String getDuetime() { return duetime; }

    public void setDuetime(String duetime) { this.duetime = duetime; }

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }

    public Byte getStatus() { return status; }

    public void setStatus(Byte status) { this.status = status; }
}
