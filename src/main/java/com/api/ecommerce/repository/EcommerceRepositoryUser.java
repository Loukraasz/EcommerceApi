package com.api.ecommerce.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ecommerce.models.EcommerceModelsUser;

public interface EcommerceRepositoryUser extends JpaRepository<EcommerceModelsUser, UUID>{
        /*metodo para encontrar o usuario pelo nome que ele definiu */
        Optional<EcommerceModelsUser> findByName(String name);
        /*metodo para encontrar o usuario pelo email */
        Optional<EcommerceModelsUser> findByEmail(String email);
        /*Metodo para encontrar o usuario pela sessionId */
        Optional<EcommerceModelsUser> findBySessionId(String sessionId);
        
        /*existem varios metodos para encontrar os usuarios de diferentes modos, isso porque dependendo da necessidade e melhor encontrar
         * o usuario pelo nome, email ou sessionid
         */
}
