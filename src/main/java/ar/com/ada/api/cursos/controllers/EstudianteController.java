package ar.com.ada.api.cursos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.ada.api.cursos.entities.Curso;
import ar.com.ada.api.cursos.entities.Estudiante;
import ar.com.ada.api.cursos.entities.Inscripcion;
import ar.com.ada.api.cursos.model.request.InscripcionRequest;
import ar.com.ada.api.cursos.model.response.GenericResponse;
import ar.com.ada.api.cursos.services.CursoService;
import ar.com.ada.api.cursos.services.EstudianteService;

@Service
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    @Autowired
    CursoService cursoService;

    @PostMapping("/api/estudiantes")
    public ResponseEntity<GenericResponse> crearEstudiante(@RequestBody Estudiante estudiante) {
        GenericResponse r = new GenericResponse();
        GenericResponse rError = new GenericResponse();

        boolean resultado = estudianteService.crearEstudiante(estudiante);
        if (resultado == false) {
            rError.isOk = false;
            rError.message = "Estudiante ya existe";
            return ResponseEntity.badRequest().body(rError);

        } else
            r.isOk = true;
        r.message = "Estudiante creado con exito";
        r.id = estudiante.getEstudianteId();
        return ResponseEntity.ok(r);
    }

    @GetMapping("/api/estudiantes")
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        return ResponseEntity.ok(estudianteService.listarEstudiantes());
    }

    @GetMapping("/api/estudiantes/{id}")
    public ResponseEntity<Estudiante> buscarPorId(@PathVariable Integer id) {
        Estudiante estudiante = estudianteService.buscarPorId(id);
        if (estudiante == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(estudiante);
    }

    @GetMapping("/api/estudiantes/{id}/cursos")
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

    @PostMapping("/api/estudiantes/{id}}/inscripciones")
    public ResponseEntity<GenericResponse> inscribir(@PathVariable Integer id,
            @RequestBody InscripcionRequest inscripcionRequest) {

        GenericResponse r = new GenericResponse();
        Inscripcion inscripcion = estudianteService.inscribir(id, inscripcionRequest.cursoId);
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