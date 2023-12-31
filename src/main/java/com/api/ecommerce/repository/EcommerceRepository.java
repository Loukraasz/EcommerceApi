package com.api.ecommerce.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ecommerce.models.EcommerceModels;

public interface EcommerceRepository extends JpaRepository<EcommerceModels, UUID>{

    /*metodo para localizar um registro pelo nome, necessariamente deve receber o nome do registro como parametro */
    Optional<EcommerceModels> findByName(String name);
    
}
