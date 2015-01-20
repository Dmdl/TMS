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
public class SessionManagement {

    private int id;
    private String sessionCode;
    private String personCode;

    public SessionManagement() {
    }

    public SessionManagement(int id, String sessionCode, String personCode) {
        this.id = id;
        this.sessionCode = sessionCode;
        this.personCode = personCode;
    }

    public SessionManagement(String sessionCode, String personCode) {
        this.sessionCode = sessionCode;
        this.personCode = personCode;
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

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

}
