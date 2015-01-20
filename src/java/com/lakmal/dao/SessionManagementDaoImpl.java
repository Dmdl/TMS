/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.dao;

import com.lakmal.domain.Session;
import com.lakmal.domain.SessionManagement;
import com.lakmal.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lakmal
 */
public class SessionManagementDaoImpl implements SessionManagementDao {

    SessionDao sessionDao = new SessionDaoImpl();

    @Override
    public int save(SessionManagement sesMan) {
        int id = -1;
        try {
            String sql = "insert into tms.session_management (session_code,person_code) values ( ?, ?)";
            Connection conn = DbConnection.getConnection();
            try (PreparedStatement preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preStmt.setString(1, sesMan.getSessionCode());
                preStmt.setString(2, sesMan.getPersonCode());
                preStmt.executeUpdate();
                ResultSet rs = preStmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                rs.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbConnection.closeConnection();
        }
        return id;
    }

    @Override
    public boolean checkOverideSessions(String personCode, String sessionCode) {
        boolean isOveride = false;
        try {
            Session currentSession = sessionDao.getSession(sessionCode);
            String date = currentSession.getDate();
            String fromTime = currentSession.getFromTime();
//            String sql = "SELECT * FROM tms.session_detail s where date='" + date + "' and '" + fromTime + "' between from_time and to_time";
            String sql = "SELECT * FROM tms.session_detail s join tms.session_management m on s.code=m.session_code where date='" + date + "' and '" + fromTime + "' between from_time and to_time and m.person_code='" + personCode + "'";
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            int count = rs.getRow();
            if (count > 0) {
                isOveride = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbConnection.closeConnection();
        }
        return isOveride;
    }

}
