package com.mcode.llp.codeGen.managers;

import com.mcode.llp.codeGen.databases.GenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryManager {
     GenDAO genDAO;

    @Autowired
    QueryManager(GenDAO genDAO) {
        this.genDAO = genDAO;
    }

    public void createTable(String entityName) {
        // Form the query using Yasar code
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + entityName + " (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "dept VARCHAR(100) NOT NULL);";
        genDAO.createTable(createTableSQL);
    }

    public void insertTable(String entityName, Map<String, Object> responseBody) {
        StringBuilder valuesPart = new StringBuilder("(" );
        StringBuilder keysPart = new StringBuilder("(");
        for (Map.Entry<String, Object> entry : responseBody.entrySet()) {
            System.out.println(entry.getValue());  // Ganesh, BCA
            valuesPart.append("'" + entry.getValue() + "'," );  // ('GANESH','BCA',
            keysPart.append(entry.getKey() + ",");
        }
        valuesPart.setCharAt(valuesPart.length() - 1, ')');
        keysPart.setCharAt(keysPart.length() - 1, ')');
        String insertTableSQL = "insert into " + entityName + keysPart + " values" + valuesPart;
        System.out.println(insertTableSQL);
        genDAO.insertTable(insertTableSQL);
    }

    public void deleteTable(String entityName, String id) {
        // Form and execute the DELETE SQL query
        String deleteTableSQL = "DELETE FROM " + entityName + " WHERE id = " + id;
        genDAO.deleteTable(deleteTableSQL);


    }

    public Map<String, Object> viewDataById(String entityName, String id) {
        String viewID = "SELECT * FROM " + entityName + " WHERE id = " + id;
        return genDAO.viewDataById(viewID);
    }

    public List<Map<String, Object>> viewAllData(String entityName) {
        String viewData = "SELECT * FROM " + entityName;
        return genDAO.viewAllData(viewData);
    }
}