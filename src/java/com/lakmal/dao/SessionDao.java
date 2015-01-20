/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.dao;

import com.lakmal.domain.Session;
import java.util.List;

/**
 *
 * @author lakmal
 */
public interface SessionDao {

    public int save(Session session);

    public List<Session> getSessions();

    public Session getSession(String sessionCode);
}
