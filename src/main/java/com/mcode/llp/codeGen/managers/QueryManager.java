package com.mcode.llp.codeGen.managers;

import com.mcode.llp.codeGen.databases.GenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryManager {
    GenDAO genDAO;

    @Autowired
    QueryManager(GenDAO genDAO) {
        this.genDAO = genDAO;
    }

    public void createTable(String entityName) {
        // Form the query using Yasar code
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + entityName + "("   + "id SERIAL PRIMARY KEY, " + "name VARCHAR(100) NOT NULL, "    + "dept VARCHAR(100) NOT NULL)";
        genDAO.createTable(createTableSQL);
    }
}
