package com.mcode.llp.codeGen.controllers;

import com.mcode.llp.codeGen.models.Property;
import com.mcode.llp.codeGen.models.Schema;
import com.mcode.llp.codeGen.services.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SchemaController {

    @Autowired
    private SchemaService schemaService;


    @PostMapping("/schemas")
    public Schema create(@RequestBody Schema schema) {
        for (Map.Entry<String, Schema> eachSchema : schema.getProperties().entrySet()) {
            Property property = new Property();
            property.setEntity(schema.getTitle());
            property.setName(eachSchema.getKey());
            property.setType(eachSchema.getValue().getType());
             if (schema.getRequired().contains(property.getName())) {
                 property.setRequired(true);
               } else {
                 property.setRequired(false);
             }
            schemaService.save(property);
        }
        return schema;
    }


    @GetMapping("/schemas/{entityName}")
    public ResponseEntity<Schema> getByName(@PathVariable(value = "entityName") String entityName) {


        Schema schema = new Schema();
        schema.setTitle(entityName);
        schema.getTitle();

        List<Property> properties = schemaService.getByName(entityName);
        if (properties != null && !properties.isEmpty()) {

            Map<String, Schema> schemaProperties = new HashMap<>();
            List <String> requiredFields = new ArrayList<>();

            for (Property property : properties) {


                Schema propertySchema = new Schema();
                schemaProperties.put(property.getName(), propertySchema);
                propertySchema.setType(property.getType());
                if (property.isRequired()) {
                    requiredFields.add(property.getName());
                }
                schema.setProperties(schemaProperties);
                if (!requiredFields.isEmpty()) {
                    schema.setRequired(requiredFields);
                }
            }
            return ResponseEntity.ok(schema);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/entities")
    public ResponseEntity<List<Schema>> getAllEntities() {
        List<String> entityNames = schemaService.getAllEntityNames();
        if (entityNames == null || entityNames.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<Schema> schemas = new ArrayList<>();

        for (String entityName : entityNames) {
            Schema schema = new Schema();
            schema.setTitle(entityName);

            List<Property> properties = schemaService.getByName(entityName);

            if (properties != null && !properties.isEmpty()) {
                Map<String, Schema> schemaProperties = new HashMap<>();
                Set<String> requiredFields = new HashSet<>();

                for (Property property : properties) {
                    Schema propertySchema = new Schema();
                    propertySchema.setType(property.getType());
                    schemaProperties.put(property.getName(), propertySchema);

                    if (property.isRequired()) {
                        requiredFields.add(property.getName());
                    }
                }
                schema.setProperties(schemaProperties);
                if (!requiredFields.isEmpty()) {
                    schema.setRequired(new ArrayList<>(requiredFields));
                }
            }

            schemas.add(schema);
        }

        return ResponseEntity.ok(schemas);
    }


}
/*

 */