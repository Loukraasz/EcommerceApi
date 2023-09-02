package com.api.ecommerce.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ecommerce.models.EcommerceModelsUser;

public interface EcommerceRepositoryUser extends JpaRepository<EcommerceModelsUser, UUID>{
        Optional<EcommerceModelsUser> findByName(String name);
        
        Optional<EcommerceModelsUser> findByEmail(String email);
        
    
}
