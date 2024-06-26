package com.mcode.llp.codeGen.models;

import jakarta.persistence.*;

@Entity
@IdClass(PropertyId.class)
public class Property {
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
