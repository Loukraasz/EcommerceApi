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
/*controller dos usuarios*/
@RestController
public class EcommerceControllerUser {
    @Autowired
    EcommerceRepositoryUser ecommerceRepository;

    /*metodo post, insere um registro no banco de dados */
    @PostMapping("/ecUserPost")
    public ResponseEntity<EcommerceModelsUser> saveEcProd(@RequestBody @Valid EcommerceDtoUser ecommerceDto){
        var ecommerceModels = new EcommerceModelsUser();
        BeanUtils.copyProperties(ecommerceDto, ecommerceModels);
        return ResponseEntity.status(HttpStatus.CREATED).body(ecommerceRepository.save(ecommerceModels));
    }
    /*metodo getAll, retorna todos os registros do banco de dados */
    @GetMapping("/ecUser")
    public ResponseEntity<List<EcommerceModelsUser>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.findAll());
    }
    /*metodo getOne, esse metodo usa o email como forma de localizar o registro do qual voce quer retorna */
    @GetMapping("/ecUser/{email}")
    public ResponseEntity<Object> getOne(@RequestBody @Valid @PathVariable(value="email" ) String email, EcommerceDtoUser ecommerceDto){
        Optional<EcommerceModelsUser> product = ecommerceRepository.findByEmail(ecommerceDto.email());
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.findByEmail(email));

    }
    /*metodo getSession, esse metodo utiliza da session id para encontrar um usuario, utilizado principalmente para checar se o usuario esta
     * logado
     */
    @GetMapping("/ecUserSID/{sessionId}")
    public ResponseEntity<Object> getSession(@RequestBody @Valid @PathVariable(value="sessionId" ) String sessionId, EcommerceDtoUser ecommerceDto){
        Optional<EcommerceModelsUser> product = ecommerceRepository.findBySessionId(ecommerceDto.sessionId());
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceRepository.findBySessionId(sessionId));

    }
    /*metodo put, utilizado para alterar um registro porem esse usa do email como parametro para localizar o registro */
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
    /*metodo delete, metodo para deletar um registro utilizando do id */
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

