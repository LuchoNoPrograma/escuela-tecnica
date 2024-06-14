package com.uap.escuela_tecnica.aplicacion.config;

import com.uap.escuela_tecnica.gestioncursos.mapper.CursoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(CursoMapper.cursoConEjecucionDtoACurso());
        modelMapper.addMappings(CursoMapper.cursoACursoConEjecucionDto());

        return modelMapper;
    }
}
