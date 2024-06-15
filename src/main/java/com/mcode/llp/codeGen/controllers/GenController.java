package com.mcode.llp.codeGen.controllers;
import com.mcode.llp.codeGen.managers.QueryManager;
import com.mcode.llp.codeGen.validators.GenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GenController {
    GenValidator genValidator = new GenValidator();
    QueryManager queryManager;

    @Autowired
    GenController(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @PostMapping("/{entityName}")
    public ResponseEntity createEntity(@RequestBody Map<String, Object> requestBody, @PathVariable(value = "entityName") String entityName) {
        boolean isEntityExists = genValidator.isEntityExists(entityName);

        if (isEntityExists) {
            queryManager.createTable(entityName);
            return new ResponseEntity<>(requestBody, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
