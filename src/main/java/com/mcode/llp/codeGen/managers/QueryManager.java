package com.mcode.llp.codeGen.managers;

import com.mcode.llp.codeGen.databases.GenDAO;

public class QueryManager {
    GenDAO genDAO = new GenDAO();

    public void createTable(String entityName) {
        // Form the query using Yasar code
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + entityName + "("   + "id SERIAL PRIMARY KEY, " + "name VARCHAR(100) NOT NULL, "    + "dept VARCHAR(100) NOT NULL)";
        genDAO.createTable(createTableSQL);
    }
}
