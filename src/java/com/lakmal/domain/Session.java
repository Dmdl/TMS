/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.domain;

/**
 *
 * @author lakmal
 */
public class Session {

    private int id;
    private String sessionCode;
    private String sessionName;
    private String courseCode;
    private String date;
    private String fromTime;
    private String toTime;

    public Session() {
    }

    public Session(int id, String sessionCode, String sessionName, String courseCode, String date, String fromTime, String toTime) {
        this.id = id;
        this.sessionCode = sessionCode;
        this.sessionName = sessionName;
        this.courseCode = courseCode;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public Session(String sessionCode, String sessionName, String courseCode, String date, String fromTime, String toTime) {
        this.sessionCode = sessionCode;
        this.sessionName = sessionName;
        this.courseCode = courseCode;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

}
