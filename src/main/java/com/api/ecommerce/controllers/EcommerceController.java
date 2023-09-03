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
import com.api.ecommerce.dtos.EcommerceDto;
import com.api.ecommerce.models.EcommerceModels;
import com.api.ecommerce.repository.EcommerceRepository;
import jakarta.validation.Valid;

@RestController
public class EcommerceController {
    @Autowired
    EcommerceRepository ecommerceRepository;

    @PostMapping("/ecProd")
    public ResponseEntity<EcommerceModels> saveEcProd(@RequestBody @Valid EcommerceDto ecommerceDto){
        var ecommerceModels = new EcommerceModels();
        BeanUtils.copyProperties(ecommerceDto, ecommerceModels);
        return ResponseEntity.status(HttpStatus.CREATED).body(ecommerceRepository.save(ecommerceModels));
    }
    @GetMapping("/ecProd")
    public ResponseEntity<List<EcommerceModels>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.findAll());
    }
    @GetMapping("/ecProd/{name}")
    public ResponseEntity<Object> getOne(@RequestBody @Valid @PathVariable(value="name" ) String name, EcommerceDto ecommerceDto){
        Optional<EcommerceModels> product = ecommerceRepository.findByName(ecommerceDto.name());
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.findByName(name));

    }

    @PutMapping("/ecProd/{name}")
    public ResponseEntity<Object>update(@PathVariable (value="name") String name, @RequestBody @Valid EcommerceDto ecommerceDto  ){
        Optional<EcommerceModels> product = ecommerceRepository.findByName(name);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }
        var product0 = product.get();
        BeanUtils.copyProperties(ecommerceDto, product0);
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.save(product0));
    }
    @DeleteMapping("/ecProd/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value="id") UUID id){
        Optional<EcommerceModels> product = ecommerceRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        var product0 = product.get();
        ecommerceRepository.delete(product0);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");

    }
}
