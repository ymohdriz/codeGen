package com.mcode.llp.codeGen.controllers;

import com.mcode.llp.codeGen.models.Property;
import com.mcode.llp.codeGen.models.Schema;
import com.mcode.llp.codeGen.services.SchemaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
    @RestController
    public class SchemaController {

        private static final Logger logger = LoggerFactory.getLogger(SchemaController.class);
        @Autowired
        private SchemaService schemaService;

        @PostMapping("/schemas")
        public ResponseEntity<?> create(@RequestBody Schema schema) {
            if (schema == null || schema.getProperties() == null || schema.getRequired() == null) {
                logger.warn("Recived Null or Empty Schema Input");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: schema is null or empty");
            }
            if (schema.getProperties().isEmpty()) {
                logger.warn("Properties field is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: properties field is empty");
            }

            if (schema.getRequired().isEmpty()) {
                logger.warn("Required field is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: required field is empty");
            }

            try {

                for (Map.Entry<String, Schema> eachSchema : schema.getProperties().entrySet()) {
                    Property property = new Property();
                    property.setEntity(schema.getTitle());
                    property.setName(eachSchema.getKey());
                    property.setType(eachSchema.getValue().getType());

                    property.setRequired(schema.getRequired().contains(property.getName()));
                    schemaService.save(property);
                }

            } catch (Exception e) {
                logger.error("Error occurred:", e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unexpected error occurred");
            }
            return ResponseEntity.ok(schema);
        }


        @GetMapping("/schemas/{entityName}")
        public ResponseEntity<Schema> getByName(@PathVariable(value = "entityName") String entityName) {


            Schema schema = new Schema();
            schema.setTitle(entityName);


            Set<Property> properties = schemaService.getByName(entityName);
            if (properties != null && !properties.isEmpty()) {

                Map<String, Schema> schemaProperties = new HashMap<>();
                Set<String> requiredFields = new HashSet<>();

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

                Set<Property> properties = schemaService.getByName(entityName);

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
                        schema.setRequired((requiredFields));
                    }
                }

                schemas.add(schema);
            }

            return ResponseEntity.ok(schemas);
        }

    }

/*

 */