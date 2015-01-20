/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.dao;

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

}
