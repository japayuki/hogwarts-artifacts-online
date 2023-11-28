package dev.gtmedia.hogwartsartifactonline.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(
                name = "gerald",
                email = "gerald.taal@gmail.com"
        ),
        description = "OpenAPI document for Hogwarts Artifact online backend",
        version = "0.0.1"
    )
)
public class OpenApiConfig {
}
