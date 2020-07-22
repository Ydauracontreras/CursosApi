package ar.com.ada.api.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.cursos.entities.Curso;
import ar.com.ada.api.cursos.repos.CursoRepository;

@Service
public class CursoService {
    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    CategoriaService categoriaService;

    /*
     * public boolean crearCurso(Curso curso) { if
     * (cursoRepository.existbyNombre(curso.getNombre())) return false;
     * grabar(curso); return true; }
     * 
     * public Curso crearCurso(String nombre, Integer categoriaId) { Curso curso =
     * new Curso(); curso.setNombre(nombre);
     * curso.agregarCategoria(categoriaService.buscarPorId(categoriaId));
     * 
     * boolean cursoNuevo = crearCurso(curso); if (cursoNuevo) return curso; else
     * return null; }
     * 
     * public void grabar(Curso curso) { cursoRepository.save(curso); }
     */

}