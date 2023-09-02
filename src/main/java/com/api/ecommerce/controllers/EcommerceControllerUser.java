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

    @PostMapping("/ecUser")
    public ResponseEntity<EcommerceModelsUser> saveEcProd(@RequestBody @Valid EcommerceDtoUser ecommerceDto){
        var ecommerceModels = new EcommerceModelsUser();
        BeanUtils.copyProperties(ecommerceDto, ecommerceModels);
        return ResponseEntity.status(HttpStatus.CREATED).body(ecommerceRepository.save(ecommerceModels));
    }
    @GetMapping("/ecUser")
    public ResponseEntity<List<EcommerceModelsUser>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.findAll());
    }
    @GetMapping("/ecUser/{name}")
    public ResponseEntity<Object> getOne(@RequestBody @Valid @PathVariable(value="name" ) String name, EcommerceDtoUser ecommerceDto){
        Optional<EcommerceModelsUser> product = ecommerceRepository.findByName(ecommerceDto.name());
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.findByName(name));

    }

    @PutMapping("/ecUser/{name}")
    public ResponseEntity<Object>update(@PathVariable (value="name") String name, @RequestBody @Valid EcommerceDtoUser ecommerceDto  ){
        Optional<EcommerceModelsUser> product = ecommerceRepository.findByName(name);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }
        var product0 = product.get();
        BeanUtils.copyProperties(ecommerceDto, product);
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.save(product0));
    }
    @DeleteMapping("/ecUser/{id}")
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
