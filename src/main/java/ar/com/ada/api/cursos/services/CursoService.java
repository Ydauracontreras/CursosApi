package ar.com.ada.api.cursos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.ada.api.cursos.entities.Curso;
import ar.com.ada.api.cursos.repos.CursoRepository;

@Service
public class CursoService {
    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    CategoriaService categoriaService;

    public boolean crearCurso(Curso curso) {
        if (cursoRepository.existsByNombre(curso.getNombre()))
            return false;
        grabar(curso);
        return true;
    }

    public Curso crearCurso(String nombre, Integer categoriaId, Integer duracionHoras, String descripcion) {
        Curso curso = new Curso();
        curso.setNombre(nombre);
        curso.agregarCategoria(categoriaService.buscarPorId(categoriaId));
        curso.setDuracionHoras(duracionHoras);
        curso.setDescripcion(descripcion);
        boolean cursoNuevo = crearCurso(curso);
        if (cursoNuevo)
            return curso;
        else
            return null;
    }

    public void grabar(Curso curso) {
        cursoRepository.save(curso);
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

}