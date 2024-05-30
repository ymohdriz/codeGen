package com.mcode.llp.codeGen.validators;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenValidator {

    public boolean isEntityExists(String entityName) {
        String filePath = "C:\\controller\\entityfile\\" + entityName + ".json";
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }
}
