package ar.com.ada.api.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.entities.Docente;
import ar.com.ada.api.cursos.model.response.GenericResponse;
import ar.com.ada.api.cursos.services.DocenteService;

import org.springframework.web.bind.annotation.*;

@RestController
public class DocenteController {
    @Autowired
    DocenteService docenteService;

    // Crear un docente
    @PostMapping(value = "/api/docentes")
    public ResponseEntity<?> crearDocente(@RequestBody Docente docente) {
        GenericResponse r = new GenericResponse();
        GenericResponse rError = new GenericResponse();

        boolean resultado = docenteService.crearDocente(docente);
        if (resultado == false) {
            rError.isOk = false;
            rError.message = "Docente ya existe";
            return ResponseEntity.badRequest().body(rError);

        } else
            r.isOk = true;
        r.message = "Docente creado con exito";
        r.id = docente.getDocenteId();
        return ResponseEntity.ok(r);
    }

    @GetMapping("/api/docentes")
    ResponseEntity<List<Docente>> listarDocentes() {
        return ResponseEntity.ok(docenteService.listartodos());
    }

    @GetMapping("/api/docentes/{id}")
    ResponseEntity<Docente> buscarPorIdDOcente(@PathVariable Integer id) {
        Docente docente = docenteService.buscarPorId(id);
        if (docente == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(docente);

    }

}