package ar.com.ada.api.cursos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.ada.api.cursos.entities.Categoria;
import ar.com.ada.api.cursos.repos.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public boolean crearCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
        return true;
    }

    public Categoria crearCategoria(String nombre, String descripcion) {
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        categoriaRepository.save(categoria);
        return categoria;
    }

    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> c = categoriaRepository.findById(id);
        if (c.isPresent())
            return c.get();
        return null;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();

    }

    public Categoria listarCategoriasById(Integer id) {
        // este metodo arroja un opcional por lo que debo evaluar si esta presente para
        // devolver el objeto
        Optional<Categoria> c = categoriaRepository.findById(id);
        if (c.isPresent())
            return c.get();
        return null;
    }
}
