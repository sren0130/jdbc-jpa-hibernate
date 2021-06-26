package com.example.jdbcmysqldemo.dao;

import java.io.*;
import java.sql.*;

// All these come from URL: https://www.youtube.com/watch?v=vwNmYVipzeA

public class SchemaInfoJdbcMetaData {
    public static void main(String[] args) throws SQLException, IOException {
        String catalog = null;
        String schemaPattern = null;
        String tableNamePattern = null;
        String columnNamePattern = null;

        String[] types = null;

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "ren0130");

            // Get metadata for
            DatabaseMetaData databaseMetaData = conn.getMetaData();

            // get list of tables
            System.out.println("List of Tables");
            System.out.println("-----------------");

            rs=databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
            }

            rs = databaseMetaData.getColumns(catalog, schemaPattern, "employee", columnNamePattern);

            while (rs.next()) {
                System.out.println(rs.getString("COLUMN_NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // set blob field with a file name
        /* */

        String sql = "update employee set resume = ?"
                 + " Where email = 'john.doe@foo.com'";

                 PreparedStatement stmt = conn.prepareStatement(sql);

                 File file = new File("sample_resule.pdf");
                 FileInputStream input = new FileInputStream(file);

                 stmt.setBinaryStream(1, input);
                 stmt.executeUpdate();


        // how to read blob out of database??
        String sql1 =  "select resume from employee where email = 'john.doe@foo.com'";
        ResultSet rs1 = stmt.executeQuery(sql1);

        File outputFile = new File("resume_from_john_doe");
        FileOutputStream output = new FileOutputStream(outputFile);
        InputStream inputStream = null;
        if (rs1.next()) {
            inputStream = rs1.getBinaryStream("resume");

            byte[] buffer = new byte[1024];

            while (inputStream.read(buffer) > 0) {
                output.write(buffer);
            }

            System.out.println("\nSaved to file: " + outputFile.getAbsolutePath());
            System.out.println("\nCompleted successfully!");
        }

        // Another large field ===  CLOB, it's for storing large text file, XML, JSON, etc, MySQL supports it up to 4GB.
        // LONGTEXT type.
    }
}
