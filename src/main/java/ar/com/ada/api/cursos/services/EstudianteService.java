package ar.com.ada.api.cursos.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.cursos.entities.Curso;
import ar.com.ada.api.cursos.entities.Estudiante;
import ar.com.ada.api.cursos.entities.Inscripcion;
import ar.com.ada.api.cursos.entities.Inscripcion.EstadoInscripcionEnum;
import ar.com.ada.api.cursos.entities.Pais.PaisEnum;
import ar.com.ada.api.cursos.entities.Pais.TipoDocuEnum;
import ar.com.ada.api.cursos.repos.EstudianteRepository;

@Service
public class EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    CursoService cursoService;

    public boolean grabar(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return true;
    }

    public boolean crearEstudiante(Estudiante estudiante) {
        if (estudianteRepository.existsEstudiante(estudiante.getPaisId(), estudiante.getTipoDocumentoId().getValue(),
                estudiante.getDocumento()))
            return false;
        grabar(estudiante);
        return true;
    }

    public Estudiante crearDocente(String nombre, PaisEnum paisEnum, TipoDocuEnum tipoDocuEnum, String documento,
            Date fechaNacimiento) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setPaisId(paisEnum);
        estudiante.setTipoDocumentoId(tipoDocuEnum);
        estudiante.setDocumento(documento);
        estudiante.setFechaNacimiento(fechaNacimiento);

        boolean estudianteNuevo = crearEstudiante(estudiante);
        if (estudianteNuevo)
            return estudiante;
        return null;
    }

    public Estudiante buscarPorId(Integer id) {
        Optional<Estudiante> porId = estudianteRepository.findById(id);
        if (porId.isPresent())
            return porId.get();
        else
            return null;
    }

    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Inscripcion inscribir(Integer estudianteId, Integer cursoId) {
        // TODO:buscar estudiante
        // buscar el curso
        // Crear la inscripcion(aprobada por defecto)
        // Asignar una inscripcion a un usuario del estudiante!
        // agregar estudiante a la lista que tiene curso;

        Estudiante estudiante = buscarPorId(estudianteId);
        Curso curso = cursoService.buscarCursoPorId(cursoId);
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCurso(curso);
        inscripcion.setFecha(new Date());
        inscripcion.setEstadoInscripcion(EstadoInscripcionEnum.ACTIVO);
        curso.agregarInscripcion(inscripcion);
        curso.asignarEstudiante(estudiante);
        estudianteRepository.save(estudiante);
        return inscripcion;
    }
}