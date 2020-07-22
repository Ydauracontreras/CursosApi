package ar.com.ada.api.cursos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    /*
     * @PostMapping("/api/cursos") public ResponseEntity<GenericResponse>
     * crearCursi(@RequestBody CursoRequest cursoRequest){ GenericResponse r = new
     * GenericResponse();
     * 
     * 
     * 
     * }
     */

}