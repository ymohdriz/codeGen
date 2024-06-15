package com.mcode.llp.codeGen.databases;

import com.mcode.llp.codeGen.models.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class GenDAO {
    private static Connection connection;
    private final AppConfig appConfig;

    @Autowired
    public GenDAO(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    private Connection createConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(appConfig.getDbURL(), appConfig.getDbUSER(), appConfig.getDbPASSWORD());
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
