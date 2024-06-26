package com.mcode.llp.codeGen.services;

import com.mcode.llp.codeGen.databases.SchemaDAO;
import com.mcode.llp.codeGen.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SchemaService {

    @Autowired
    private  SchemaDAO schemaRepository;

    public Property save(Property schemas) {
        return schemaRepository.save(schemas);
    }

    public List<Property> getAll() {
        return schemaRepository.findAll();
    }

    public Optional<Property> findById(Long id) {
        return schemaRepository.findById(id);
    }


}
