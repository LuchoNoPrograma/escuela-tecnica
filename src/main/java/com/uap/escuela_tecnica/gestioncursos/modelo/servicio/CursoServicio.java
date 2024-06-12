package com.uap.escuela_tecnica.gestioncursos.modelo.servicio;

import com.uap.escuela_tecnica.gestioncursos.modelo.dao.ICursoDao;
import com.uap.escuela_tecnica.gestioncursos.modelo.dao.ICursoEjecucionDao;
import com.uap.escuela_tecnica.gestioncursos.modelo.entidad.Curso;
import com.uap.escuela_tecnica.gestioncursos.modelo.entidad.CursoEjecucion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServicio {
    private final ICursoDao cursoDao;
    private final ICursoEjecucionDao cursoEjecucionDao;

    public Curso registrarCursoSinEjecucion(Curso curso) {
        return cursoDao.save(curso);
    }

    @Transactional
    public Curso registrarCursoConEjecucion(Curso curso) {
        Curso cursoRegistrado = cursoDao.save(curso);

        CursoEjecucion cursoEjecucion = curso.getListaCursoEjecucion().get(0);

        //Si es nulo, entonces false, caso contrario, lo que se pone enEjecucion
        cursoEjecucion.setEnEjecucion(cursoEjecucion.getEnEjecucion() != null && cursoEjecucion.getEnEjecucion());
        cursoEjecucion.setCurso(cursoRegistrado);

        CursoEjecucion cursoEjecucionRegistrado = cursoEjecucionDao.save(curso.getListaCursoEjecucion().get(0));

        cursoRegistrado.setListaCursoEjecucion(List.of(cursoEjecucionRegistrado));
        return cursoRegistrado;
    }

    //Se realiza la operacion desde la perspectiva de un curso, NO DE SU EJECUCION
    @Transactional(readOnly = true)
    public List<Curso> listarCursosConEjecucionActual() {
        return cursoEjecucionDao.listarCursosEnEjecucionConCurso().stream().map(ce -> {
            Curso curso = ce.getCurso();
            ce.setCurso(null);
            curso.setListaCursoEjecucion(List.of(ce));
            return curso;
        }).toList();
    }
}
