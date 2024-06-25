package com.mcode.llp.codeGen.services;

import com.mcode.llp.codeGen.databases.SchemaDAO;
import com.mcode.llp.codeGen.models.Property;
import com.mcode.llp.codeGen.models.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchemaService {

    @Autowired
    private SchemaDAO schemaRepository;

    public Property save(Property schemas) {
        return schemaRepository.save(schemas);
    }

}
