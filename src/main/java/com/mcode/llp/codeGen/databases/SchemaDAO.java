package com.mcode.llp.codeGen.databases;

import com.mcode.llp.codeGen.models.Property;
import com.mcode.llp.codeGen.models.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public @Repository  interface SchemaDAO extends JpaRepository<Property, Long> {
    @Query(value = "SELECT *  FROM property WHERE entity = :entityname", nativeQuery = true)
    List<Property> findByEntityName(@Param("entityname") String entityname);


}


