package com.mcode.llp.codeGen.databases;

import com.mcode.llp.codeGen.models.Property;
import com.mcode.llp.codeGen.models.Schema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchemaDAO extends JpaRepository<Property, Long> {

}
