/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.dao;

import com.lakmal.domain.Course;
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
public class CourseDaoImpl implements CourseDao {

    DbConnection dbConn;

    public CourseDaoImpl() {
        dbConn = new DbConnection();
    }

    @Override
    public int save(Course course) {
        int id = -1;
        try {
            String sql = "insert into tms.course (code, name) values ( ?, ?)";
            Connection conn = DbConnection.getConnection();
            try (PreparedStatement preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preStmt.setString(1, course.getCode());
                preStmt.setString(2, course.getName());
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
    public List<Course> getCourses() {
        List<Course> courses = null;
        try {
            String sql = "SELECT * FROM tms.course";
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            try (ResultSet rs = stmt.executeQuery(sql)) {
                courses = new ArrayList<>();
                while (rs.next()) {
                    Course person = new Course(rs.getInt("id"), rs.getString("code"), rs.getString("name"));
                    courses.add(person);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbConnection.closeConnection();
        }
        return courses;
    }
}
