/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.dao;

import com.lakmal.domain.Person;
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
public class PersonDaoImpl implements PersonDao {

    DbConnection dbConn;

    public PersonDaoImpl() {
        dbConn = new DbConnection();
    }

    @Override
    public int save(Person person) {
        int id = -1;
        try {
            String sql = "insert into tms.person (code, name) values ( ?, ?)";
            Connection conn = DbConnection.getConnection();
            try (PreparedStatement preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preStmt.setString(1, person.getCode());
                preStmt.setString(2, person.getName());
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
    public List<Person> getResourcePersons() {
        List<Person> persons = null;
        try {
            String sql = "SELECT * FROM tms.person";
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            try (ResultSet rs = stmt.executeQuery(sql)) {
                persons = new ArrayList<>();
                while (rs.next()) {
                    Person person = new Person(rs.getInt("id"), rs.getString("code"), rs.getString("name"));
                    persons.add(person);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbConnection.closeConnection();
        }
        return persons;
    }
}
