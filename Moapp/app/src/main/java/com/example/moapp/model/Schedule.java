package com.example.moapp.model;

public class Schedule {
    private String startTime;
    private String endTime;
    private String description;
    private String summary;

    public Schedule() {


    }

    public Schedule(String startTime, String endTime, String summary, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.summary = summary;
        this.description = description;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
