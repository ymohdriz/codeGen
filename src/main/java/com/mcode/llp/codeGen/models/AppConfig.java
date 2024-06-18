package com.mcode.llp.codeGen.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix =  "app.config")

public class AppConfig {
    String dbURL;
    String dbUSER;
    String dbPASSWORD;

    public String getDbURL() {
        return dbURL;
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public String getDbUSER() {
        return dbUSER;
    }

    public void setDbUSER(String dbUSER) {
        this.dbUSER = dbUSER;
    }

    public String getDbPASSWORD() {
        return dbPASSWORD;
    }

    public void setDbPASSWORD(String dbPASSWORD) {
        this.dbPASSWORD = dbPASSWORD;
    }
}
