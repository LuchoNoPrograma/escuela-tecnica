package com.uap.escuela_tecnica.gestioncursos.mapper;

import com.uap.escuela_tecnica.gestioncursos.dto.CursoConEjecucionDto;
import com.uap.escuela_tecnica.gestioncursos.dto.CursoEjecucionDto;
import com.uap.escuela_tecnica.gestioncursos.modelo.entidad.Curso;
import com.uap.escuela_tecnica.gestioncursos.modelo.entidad.CursoEjecucion;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import java.util.List;

//Clases involucradas: Curso y CursoConEjecucionDto
public class CursoMapper {

    //Configuracion personalizada para mapear de un Dto a Entidad
    public static PropertyMap<CursoConEjecucionDto, Curso> cursoConEjecucionDtoACurso() {
        //Este convertidor transforma un atributo, a otro atributo
        //CursoEjecucionDto.cursoEjecucion -> CursoEjecucion.listaCursoEjecucion
        Converter<CursoEjecucionDto, List<CursoEjecucion>> converterCursoEjecucionDtoAListaCursoEjecucion = context -> {
            CursoEjecucionDto source = context.getSource();

            CursoEjecucion cursoEjecucion = new CursoEjecucion();
            cursoEjecucion.setFechaInicio(source.getFechaInicio());
            cursoEjecucion.setFechaFin(source.getFechaFin());
            cursoEjecucion.setFechaLimite(source.getFechaLimite());
            cursoEjecucion.setCertificadoImg(source.getCertificadoImg());

            List<CursoEjecucion> destination = List.of(cursoEjecucion);
            return destination;
        };

        //Definiremos el mapeador de un CursoConEjecucionDto a un Curso
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                //Aqui indico que usare un convertidor en el getter al setter:
                //1) cursoEjecucionDto.getCursoEjecucion() -> cursoEjecucion.setListaCursoEjecucion()
                using(converterCursoEjecucionDtoAListaCursoEjecucion).map(source.getCursoEjecucion()).setListaCursoEjecucion(null);
            }
        };
    }

    //Configuracion personalizada para mapear de un Entidad a Dto
    public static PropertyMap<Curso, CursoConEjecucionDto> cursoACursoConEjecucionDto() {
        //Este convertidor transforma un atributo, a otro atributo
        //Curso.listaCursoEjecucion -> CursoConEjecucionDto.cursoEjecucion
        Converter<List<CursoEjecucion>, CursoEjecucionDto> converterListaCursoEjecucionACursoConEjecucionDto = context -> {
            List<CursoEjecucion> source = context.getSource();

            if (source == null || source.isEmpty()) {
                return null;
            }

            CursoEjecucionDto destination = new CursoEjecucionDto();
            destination.setIdCursoEjecucion(source.get(0).getIdCursoEjecucion());
            destination.setFechaInicio(source.get(0).getFechaInicio());
            destination.setFechaFin(source.get(0).getFechaFin());
            destination.setFechaLimite(source.get(0).getFechaLimite());
            destination.setCertificadoImg(source.get(0).getCertificadoImg());
            destination.setEnEjecucion(source.get(0).getEnEjecucion());
            destination.setEstado(source.get(0).getEstado());

            return destination;
        };

        //Definiremos el mapeador de un Curso a un CursoConEjecucionDto
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                //Aqui indico que usare un convertidor en el getter al setter:
                //1) curso.getListaCursoEjecucion() -> cursoConEjecucionDto.getCursoEjecucion()
                using(converterListaCursoEjecucionACursoConEjecucionDto).map(source.getListaCursoEjecucion()).setCursoEjecucion(null);
            }
        };
    }

}