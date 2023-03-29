package com.lendtech.mselevator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Lendtech-elevator-service",
                version = "1.0.0",
                description = "This is an elevator evaluation microservice ",
                termsOfService = "test-code",
                contact = @Contact(
                        name = "Derrick Dickens Angolo Otina",
                        email = "derrick.angolo@live.com"
                ),
                license = @License(
                        name = "license",
                        url = "test-code"
                )
        ),
        servers = {@Server(url = "http://localhost:8080")},
        tags = {@Tag(name = "Lendtech-elevator", description = "This is the Lendtech-elevator service.")}
)
@SecurityScheme(name = "Auth", type = SecuritySchemeType.HTTP, scheme = "Basic Auth", description = "Basic auth - username and password")
public class MsElevatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsElevatorApplication.class, args);
    }

}
