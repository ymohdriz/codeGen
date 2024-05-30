package com.mcode.llp.codeGen.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GenDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/mukesh";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";
    private static Connection connection;

    private Connection createConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Connection failure.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void createTable(String createTableSQL) {
        try (Statement statement = createConnection().createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating table.");
            e.printStackTrace();
        }
    }
}
