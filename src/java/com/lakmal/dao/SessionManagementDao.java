/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.dao;

import com.lakmal.domain.SessionManagement;

/**
 *
 * @author lakmal
 */
public interface SessionManagementDao {

    public int save(SessionManagement sesMan);

    public boolean checkOverideSessions(String personCode, String sessionCode);
}
