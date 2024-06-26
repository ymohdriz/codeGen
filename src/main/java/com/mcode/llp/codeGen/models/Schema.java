package com.mcode.llp.codeGen.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class Schema {
    String entity;

    List<Property> properties = new ArrayList<>();

    public Schema() {
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
