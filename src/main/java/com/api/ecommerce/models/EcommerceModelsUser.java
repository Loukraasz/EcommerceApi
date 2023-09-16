package com.api.ecommerce.models;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ec_users")
public class EcommerceModelsUser {
    @Id
    @GeneratedValue
    private UUID Id;
    
    private String email;

    private String name;
    
    private String password;

    private String sessionId;
    
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }


    
}

