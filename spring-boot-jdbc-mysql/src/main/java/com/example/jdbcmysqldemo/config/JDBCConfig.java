package com.example.jdbcmysqldemo.config;

import lombok.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConfig {

    private static final String jdbc_url = "jdbc:mysql://localhost:3306/bank?useSSL=false";

    // username/password
    public Connection getConnection(String url) throws SQLException {
        return DriverManager.getConnection(url, "root", "ren0130");
    }
}
