package ar.com.ada.api.cursos.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.entities.Curso;
import ar.com.ada.api.cursos.model.request.CursoMRequest;
import ar.com.ada.api.cursos.model.request.CursoRequest;
import ar.com.ada.api.cursos.model.response.GenericResponse;
import ar.com.ada.api.cursos.services.CategoriaService;
import ar.com.ada.api.cursos.services.CursoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CursoController {

    @Autowired
    CursoService cursoService;

    @Autowired
    CategoriaService categoriaService;

    // Crear un Curso ingresando nombre, categoriaId, duracion y descripcion
    @PostMapping("/api/cursos")
    public ResponseEntity<GenericResponse> crearCursi(@RequestBody CursoRequest cursoRequest) {
        GenericResponse r = new GenericResponse();
        Curso resultado = cursoService.crearCurso(cursoRequest.nombre, cursoRequest.categoriaId,
                cursoRequest.duracionHoras, cursoRequest.descripcion);
        if (resultado == null) {
            return ResponseEntity.badRequest().build();
        } else
            r.isOk = true;
        r.message = "Curso creado con exito";
        r.id = resultado.getCursoId();
        return ResponseEntity.ok(r);
    }

    // Trae el listado completo de cursos
    @GetMapping("/api/cursos")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> lista = cursoService.listarCursos();
        return ResponseEntity.ok(lista);
    }

    // Trae un curso segun su Id
    @GetMapping("/api/cursos/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Integer id) {
        Curso curso = cursoService.buscarCursoPorId(id);
        if (curso == null) {
            return ResponseEntity.badRequest().build();
        } else
            return ResponseEntity.ok(curso);
    }

    // Trea el listado de cursos con una misma categoria
    // Este metodo no anda:/
    @GetMapping("/api/cursos/categorias/{categoria_id}")
    public ResponseEntity<List<Curso>> listarCursoPorCategoriaId(
            @PathVariable(value = "categoria_id") Integer categoriaId) {
        return ResponseEntity.ok(categoriaService.listarCategoriasById(categoriaId).getCursos());
    }

    // Editar nombre, duracion o descripcion de un curso
    @PutMapping("/api/cursos/{id}")
    public ResponseEntity<?> editarCurso(@PathVariable Integer id, @RequestBody CursoMRequest cursoRequest) {
        Curso cursoModificado = cursoService.buscarCursoPorId(id);
        if (cursoModificado == null) {
            return ResponseEntity.badRequest().build();
        }

        else
            cursoModificado.setNombre(cursoRequest.nombre);
        cursoModificado.setDuracionHoras(cursoRequest.duracionHoras);
        cursoModificado.setDescripcion(cursoRequest.descripcion);
        GenericResponse r = new GenericResponse();
        boolean resultado = cursoService.modificarCurso(cursoModificado);
        if (resultado) {
            r.isOk = true;
            r.id = cursoModificado.getCursoId();
            r.message = "Modificaste el curso con éxito.";
            return ResponseEntity.ok(r);
        } else {
            r.isOk = false;
            r.message = "No pudiste Modificar el esta curso";
            return ResponseEntity.badRequest().body(r);
        }

    }
}

// Cursos por Estudiante
// Cursos por Docente
// Cursos por cantidad de inscriptos
