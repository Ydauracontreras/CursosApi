package ar.com.ada.api.cursos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.model.request.*;
import ar.com.ada.api.cursos.model.response.*;
import ar.com.ada.api.cursos.services.*;

@Controller
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    @Autowired
    CursoService cursoService;

    //
    // sandbox692c416868b54969837723951831889f.mailgun.org

    @PostMapping("/api/estudiantes")
    public ResponseEntity<GenericResponse> crearEstudiante(@RequestBody Estudiante estudiante) {
        GenericResponse r = new GenericResponse();
        if (estudianteService.crearEstudiante(estudiante)) {
            r.isOk = true;
            r.message = "Estudiante creado con exito";
            r.id = estudiante.getEstudianteId();
            return ResponseEntity.ok(r);
        } else
            r.isOk = false;
        r.message = "Estudiante ya existe";
        return ResponseEntity.badRequest().body(r);

    }

    @GetMapping("/api/estudiantes")
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        return ResponseEntity.ok(estudianteService.listarEstudiantes());
    }

    @GetMapping("/api/estudiantes/{id}")
    @PreAuthorize("hasAuthority('CLAIM_userType_STAFF') or (hasAuthority('CLAIM_userType_ESTUDIANTE') and hasAuthority('CLAIM_entityId_'+#id))")
    public ResponseEntity<Estudiante> buscarPorId(@PathVariable Integer id) {
        Estudiante estudiante = estudianteService.buscarPorId(id);
        if (estudiante == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(estudiante);
    }

    @GetMapping("/api/estudiantes/{id}/cursos")
    @PreAuthorize("hasAuthority('CLAIM_userType_STAFF') or (hasAuthority('CLAIM_userType_ESTUDIANTE') and hasAuthority('CLAIM_entityId_'+#id))")
    public ResponseEntity<?> cursosPorEstudiante(@PathVariable Integer id,
            @RequestParam(value = "disponibles", required = false) boolean disponibles) {
        List<Curso> listaCursos = new ArrayList<>();
        if (disponibles) {
            listaCursos = cursoService.listarCursosSinElEstudiante(estudianteService.buscarPorId(id));
        } else {
            // me trae los cursos donde el estudiante ya esta inscritoo
            listaCursos = estudianteService.buscarPorId(id).getCursosQueAsiste();
        }

        return ResponseEntity.ok(listaCursos);
    }

    @PostMapping("/api/estudiantes/{id}/inscripciones")
    public ResponseEntity<GenericResponse> inscribir(@PathVariable Integer id,
            @RequestBody InscripcionRequest inscripcionRequest) {
        Inscripcion inscripcion = estudianteService.inscribir(id, inscripcionRequest.cursoId);
        GenericResponse r = new GenericResponse();

        if (inscripcion == null) {
            r.isOk = false;
            r.message = "No se puede inscribir a este curso";
            return ResponseEntity.badRequest().body(r);
        } else

            r.isOk = true;
        r.message = "Estudiante inscripto con exito";
        r.id = inscripcion.getInscripcionId();
        return ResponseEntity.ok(r);

    }

}