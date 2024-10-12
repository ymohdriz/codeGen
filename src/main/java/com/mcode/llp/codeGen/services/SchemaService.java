package com.mcode.llp.codeGen.services;

import com.mcode.llp.codeGen.databases.SchemaDAO;
import com.mcode.llp.codeGen.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import java.util.Optional;
@Service
public class SchemaService {

    @Autowired
    private  SchemaDAO schemaRepository;


    public Property save(Property schemas) {
        return schemaRepository.save(schemas);
    }

    public List<Property> getAll(String entityName) {
        return schemaRepository.findAll();
    }

    public List<Property> getByName(String entityName) {
        return schemaRepository.findByEntityName(entityName);
    }

    public List<String> getAllEntityNames(){return schemaRepository.getAllEntityNames();}
    /* {
        List<Property> allProperties = schemaRepository.findAll();
        Set<String> entityNames = new HashSet<>();

        for (Property property : allProperties) {
            entityNames.add(property.getEntity());
        }

        return new ArrayList<>(entityNames);
    }*/

}
