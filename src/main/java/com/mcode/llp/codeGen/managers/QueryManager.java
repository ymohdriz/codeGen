package com.mcode.llp.codeGen.managers;

import com.mcode.llp.codeGen.databases.GenDAO;
import com.mcode.llp.codeGen.models.Property;
import com.mcode.llp.codeGen.models.PropertyId;
import com.mcode.llp.codeGen.models.Schema;
import com.mcode.llp.codeGen.services.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryManager {
    GenDAO genDAO;
    @Autowired
    SchemaService schemaService;

    @Autowired
    QueryManager(GenDAO genDAO) {
        this.genDAO = genDAO;
    }


    public void createTable(String entityName) {
        // Fetch all properties
        List<Property> properties = schemaService.getAll();
        StringBuilder createTableSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(entityName)
                .append(" (id SERIAL PRIMARY KEY");

        for (Property property : properties) {
            if (property.getEntity().equals(entityName)) {    // students, student
                String dbType = null;
                if (property.getType().equals("string")) {
                    dbType = "varchar(255)";
                } else {
                    dbType = property.getType();
                }
                createTableSQL.append(", ")
                        .append(property.getName())
                        .append(" ")
                        .append(dbType);
            }
        }
        createTableSQL.append(");");
        System.out.println(createTableSQL.toString());
        genDAO.createTable(createTableSQL.toString());
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


    public void updateTable(String entityName, String id, Map<String, Object> updates) {
        StringBuilder updateSQL = new StringBuilder("UPDATE ").append(entityName).append(" SET ");

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            updateSQL.append(entry.getKey())
                    .append(" = '")
                    .append(entry.getValue())
                    .append("', ");
        }
        updateSQL.setLength(updateSQL.length() - 2);
        updateSQL.append(" WHERE id = ").append(id);
        System.out.println(updateSQL.toString());
        genDAO.updateTable(updateSQL.toString());
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