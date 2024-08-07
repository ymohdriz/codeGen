package com.mcode.llp.codeGen.controllers;

import com.mcode.llp.codeGen.models.Property;
import com.mcode.llp.codeGen.services.SchemaService;
import com.mcode.llp.codeGen.models.Schema;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class SchemaController {

    @Autowired
    private SchemaService schemaService;
    @PostMapping("/schemas")
    public Schema create(@RequestBody Schema schema) {
        for (Property property : schema.getProperties()) {
            property.setEntity(schema.getEntity());
            schemaService.save(property);
        }
        return schema;
    }




@GetMapping("/schemas/{entityName}")
    public ResponseEntity<Schema> getbyName(@PathVariable(value = "entityName") String entityName) {
        List<Property> properties = schemaService.getByName(entityName);
        if (properties != null && !properties.isEmpty()) {
            Schema schema = new Schema();
            for (Property property : properties) {
                schema.setEntity(property.getEntity());
                property.setEntity(null);
            }
            schema.setProperties(properties);
            return  ResponseEntity.ok(schema);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
