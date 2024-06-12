package com.uap.escuela_tecnica.gestioncursos.controlador;

import com.uap.escuela_tecnica.gestioncursos.dto.CursoConEjecucionDto;
import com.uap.escuela_tecnica.gestioncursos.dto.CursoSinEjecucionDto;
import com.uap.escuela_tecnica.gestioncursos.modelo.entidad.Curso;
import com.uap.escuela_tecnica.gestioncursos.modelo.servicio.CursoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class CursoApi {
    private final CursoServicio cursoServicio;
    private final ModelMapper modelMapper;

    @PostMapping("/curso/sin-ejecucion")
    public ResponseEntity<CursoSinEjecucionDto> registrarCursoSinEjecucion(@Valid @RequestBody CursoSinEjecucionDto cursoSinEjecucionDto) {
        Curso curso = modelMapper.map(cursoSinEjecucionDto, Curso.class);
        Curso cursoRegistrado = cursoServicio.registrarCursoSinEjecucion(curso);
        return ResponseEntity.status(201).body(modelMapper.map(cursoRegistrado, CursoSinEjecucionDto.class));
    }


    @PostMapping("/curso/con-ejecucion")
    public ResponseEntity<CursoConEjecucionDto> registrarCursoConEjecucion(@Valid @RequestBody CursoConEjecucionDto cursoConEjecucionDto) {
        //Aca se utiliza un mapeador personalizado, esta en CursoMapper
        Curso cursoRegistrado = cursoServicio.registrarCursoConEjecucion(modelMapper.map(cursoConEjecucionDto, Curso.class));

        //Aca igual se utiliza un mapeador personalizado, esta en CursoMapper
        return ResponseEntity.status(201).body(modelMapper.map(cursoRegistrado, CursoConEjecucionDto.class));
    }

    @GetMapping("/curso/con-ejecucion")
    public ResponseEntity<List<CursoConEjecucionDto>> listarCursosConEjecucion() {
        //Tipado de model mapper para los List<?>
        Type tipadoListaCursoConEjecucionDto = new TypeToken<List<CursoConEjecucionDto>>() {
        }.getType();


        return ResponseEntity.status(200).body(modelMapper.map(cursoServicio.listarCursosConEjecucionActual(), tipadoListaCursoConEjecucionDto));
    }
}
