package com.mcode.llp.codeGen.controllers;

import com.mcode.llp.codeGen.models.Property;
import com.mcode.llp.codeGen.services.SchemaService;
import com.mcode.llp.codeGen.models.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
