/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lakmal
 */
public class DbConnection {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/tms";
    static final String USER = "root";
    static final String PASS = "root";
    static Connection conn = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public ResultSet executeSelect(String sql) {
        ResultSet rs = null;
        try {
            if (null == conn) {
                conn = getConnection();
            }
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public PreparedStatement executeInsert(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            if (null == conn) {
                conn = getConnection();
            }
            preparedStatement = conn.prepareStatement(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return preparedStatement;
    }

    public static void closeConnection() {
        try {
            if (null != conn) {
                conn.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
