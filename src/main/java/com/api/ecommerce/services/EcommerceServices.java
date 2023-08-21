package com.api.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.ecommerce.models.EcommerceModels;
import com.api.ecommerce.repository.EcommerceRepository;

public class EcommerceServices {

    @Autowired
    EcommerceRepository ecommerceRepository;

    public Optional<EcommerceModels> findByName(String name){
        return ecommerceRepository.findByName(name);
    }
    
}
