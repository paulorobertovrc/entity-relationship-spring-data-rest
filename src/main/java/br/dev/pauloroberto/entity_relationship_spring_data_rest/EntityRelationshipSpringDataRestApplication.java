package br.dev.pauloroberto.entity_relationship_spring_data_rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Entity Relationship with Spring Data", version = "1.0", description = "A mini project to demonstrate the use of Spring Data JPA and Swagger."))
public class EntityRelationshipSpringDataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntityRelationshipSpringDataRestApplication.class, args);
    }

}
