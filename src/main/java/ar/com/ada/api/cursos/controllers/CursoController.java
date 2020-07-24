package ar.com.ada.api.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.entities.Curso;
import ar.com.ada.api.cursos.model.request.CursoRequest;
import ar.com.ada.api.cursos.model.response.GenericResponse;
import ar.com.ada.api.cursos.services.CursoService;

@RestController
public class CursoController {

    @Autowired
    CursoService cursoService;

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

    @GetMapping("/api/cursos")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> lista = cursoService.listarCursos();
        return ResponseEntity.ok(lista);
    }

}