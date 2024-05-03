package com.mcode.llp.codeGen.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class GenController {

    @PostMapping("/{entityName}")
    public ResponseEntity createEntity(@RequestBody Map<String, Object> requestBody, @PathVariable(value = "entityName") String entityName) {
        ResponseEntity responseEntity = new ResponseEntity(requestBody, HttpStatus.CREATED);
        return responseEntity;
    }
}
