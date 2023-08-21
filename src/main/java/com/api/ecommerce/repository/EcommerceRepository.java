package com.api.ecommerce.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ecommerce.models.EcommerceModels;

public interface EcommerceRepository extends JpaRepository<EcommerceModels, UUID>{

    Optional<EcommerceModels> findByName(String name);
    
}
