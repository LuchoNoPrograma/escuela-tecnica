package com.uap.escuela_tecnica.aplicacion.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }


    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info()
                        .version("v1")
                        .title("API REST de la Escuela Técnica")
                        .description("""
                                Documentación de la API de la Escuela Técnica.

                                Consideraciones:

                                #1 Se requiere un token de autenticación para acceder a las rutas que comienzan con /api.

                                #2 No se requiere un token de autenticación para acceder a las rutas que no comienzan dentro del conjunto {"/api"}.

                                #3 Para autenticarse y obtener un JWT, debe ingresar un usuario y contraseña válido en la petición POST /jwt-auth/generar-token.

                                #4 Debe copiar el JWT generado en la petición POST /jwt-auth/generar-token y pegarlo en el menú Authorize.
                                """));

    }
}