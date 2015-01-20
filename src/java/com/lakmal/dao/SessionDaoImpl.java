/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.dao;

import com.lakmal.domain.Session;
import com.lakmal.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lakmal
 */
public class SessionDaoImpl implements SessionDao {

    @Override
    public int save(Session session) {
        int id = -1;
        try {
            String sql = "insert into tms.session_detail (code, name,course_code,date,from_time,to_time) values ( ?, ?,?,?,?,?)";
            Connection conn = DbConnection.getConnection();
            try (PreparedStatement preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preStmt.setString(1, session.getSessionCode());
                preStmt.setString(2, session.getSessionName());
                preStmt.setString(3, session.getCourseCode());
                preStmt.setString(4, session.getDate());
                preStmt.setString(5, session.getFromTime());
                preStmt.setString(6, session.getToTime());
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
    public List<Session> getSessions() {
        List<Session> sessions = null;
        try {
            String sql = "SELECT * FROM tms.session_detail";
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            try (ResultSet rs = stmt.executeQuery(sql)) {
                sessions = new ArrayList<>();
                while (rs.next()) {
                    Session session = new Session(rs.getInt("id"), rs.getString("code"), rs.getString("name"), rs.getString("course_code"), rs.getString("date"), rs.getString("from_time"), rs.getString("to_time"));
                    sessions.add(session);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbConnection.closeConnection();
        }
        return sessions;
    }

    @Override
    public Session getSession(String sessionCode) {
        Session session = null;
        try {
            String sql = "SELECT * FROM tms.session_detail s where code='" + sessionCode + "'";
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    session = new Session(rs.getInt("id"), rs.getString("code"), rs.getString("name"), rs.getString("course_code"), rs.getString("date"), rs.getString("from_time"), rs.getString("to_time"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbConnection.closeConnection();
        }
        return session;
    }
}
