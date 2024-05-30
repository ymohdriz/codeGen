package com.mcode.llp.codeGen.controllers;

import com.mcode.llp.codeGen.managers.QueryManager;
import com.mcode.llp.codeGen.validators.GenValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class GenController {
    GenValidator genValidator = new GenValidator();
    QueryManager queryManager = new QueryManager();

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
