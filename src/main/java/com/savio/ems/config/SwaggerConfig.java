package com.savio.ems.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = { @Server(url = "http://localhost:8080") },
        info = @Info(title = "EMPLOYEE MANAGEMENT SYSTEM API",
                version = "v1",
                description = "This is a simple demonstration of restful APIs on employee management system",
                contact = @Contact(url = "https://www.linkedin.com/in/savio-kalinga-82652a18a/",
                        name = "Savio Kalinga")))

public class SwaggerConfig {

}
