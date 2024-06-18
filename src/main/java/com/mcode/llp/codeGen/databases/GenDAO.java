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
                System.out.println("Connection created Succesfully");
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

    public void insertTable(String insertTableSQL) {
        try (Connection connection = createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL)) {
            preparedStatement.executeUpdate();
            System.out.println("Data inserted into the table successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting into table.");
            e.printStackTrace();
        }

    }

    public void deleteTable(String deleteTableSQL) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteTableSQL)) {
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data deleted from the table successfully.");
            } else {
                System.out.println("No data found to delete.");
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error deleting from table.");
            e.printStackTrace();
        }
    }
}

