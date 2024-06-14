package com.uap.escuela_tecnica;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Log4j2
@SpringBootApplication
public class EscuelaTecnicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EscuelaTecnicaApplication.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return args -> {
            log.info("Aplicación iniciado con éxito");
        };
    }
}
