package com.mcode.llp.codeGen.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@IdClass(PropertyId.class)
public class Property {

    private long id;
    @Id
    private String name;

    private String type;
    @Id
    private String entity;

    // Getters and setters
    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
