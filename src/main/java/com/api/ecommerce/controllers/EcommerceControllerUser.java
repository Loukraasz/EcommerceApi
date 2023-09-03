package com.api.ecommerce.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.api.ecommerce.dtos.EcommerceDtoUser;
import com.api.ecommerce.models.EcommerceModelsUser;
import com.api.ecommerce.repository.EcommerceRepositoryUser;

import jakarta.validation.Valid;

@RestController
public class EcommerceControllerUser {
    @Autowired
    EcommerceRepositoryUser ecommerceRepository;

    @PostMapping("/ecUserPost")
    public ResponseEntity<EcommerceModelsUser> saveEcProd(@RequestBody @Valid EcommerceDtoUser ecommerceDto){
        var ecommerceModels = new EcommerceModelsUser();
        BeanUtils.copyProperties(ecommerceDto, ecommerceModels);
        return ResponseEntity.status(HttpStatus.CREATED).body(ecommerceRepository.save(ecommerceModels));
    }
    @GetMapping("/ecUser")
    public ResponseEntity<List<EcommerceModelsUser>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.findAll());
    }
    @GetMapping("/ecUser/{email}")
    public ResponseEntity<Object> getOne(@RequestBody @Valid @PathVariable(value="email" ) String email, EcommerceDtoUser ecommerceDto){
        Optional<EcommerceModelsUser> product = ecommerceRepository.findByEmail(ecommerceDto.email());
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.findByEmail(email));

    }

    @PutMapping("/ecUserPut/{email}")
    public ResponseEntity<Object>update(@PathVariable (value="email") String email, @RequestBody @Valid EcommerceDtoUser ecommerceDto  ){
        Optional<EcommerceModelsUser> product = ecommerceRepository.findByEmail(email);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }
        var product0 = product.get();
        BeanUtils.copyProperties(ecommerceDto, product0);
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.save(product0));
    }
    @DeleteMapping("/ecUserDelete/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value="id") UUID id){
        Optional<EcommerceModelsUser> product = ecommerceRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        var product0 = product.get();
        ecommerceRepository.delete(product0);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");

    }
}

