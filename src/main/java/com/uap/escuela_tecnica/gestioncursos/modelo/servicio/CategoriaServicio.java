package com.uap.escuela_tecnica.gestioncursos.modelo.servicio;

import com.uap.escuela_tecnica.gestioncursos.modelo.dao.ICategoriaDao;
import com.uap.escuela_tecnica.gestioncursos.modelo.entidad.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaServicio {
    private final ICategoriaDao categoriaDao;

    public Categoria crearCategoria(String nombre) {
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);

        return categoriaDao.save(categoria);
    }
}
