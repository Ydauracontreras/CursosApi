package ar.com.ada.api.cursos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.ada.api.cursos.entities.Curso;
import ar.com.ada.api.cursos.entities.Docente;
import ar.com.ada.api.cursos.repos.CursoRepository;

@Service
public class CursoService {
    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    DocenteService docenteService;

    public boolean crearCurso(Curso curso) {
        if (cursoRepository.existsByNombre(curso.getNombre()))
            return false;
        else
            grabar(curso);
        return true;
    }

    public boolean modificarCurso(Curso curso) {
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

    public Curso buscarCursoPorId(Integer id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            return curso.get();
        } else
            return null;

    }

    public boolean asignarDocente(Integer cursoId, Integer docenteId) {
        Curso curso = new Curso();
        this.buscarCursoPorId(cursoId);
        List<Docente> docentes = curso.getDocentes();
        for (Docente docente : docentes) {
            docente.getDocenteId().equals(docenteId);
            return false;
        }
        Docente docente = docenteService.buscarPorId(docenteId);
        // Relacion Bidireccional de docente
        // Asigno el docente al curso
        curso.asignarDocente(docente);

        // Actualizo la base de datos
        cursoRepository.save(curso);
        return true;
    }

    public List<Curso> listarCursosSinDocentes() {
        List<Curso> cursoSinDocentes = new ArrayList<>();
        for (Curso curso : listarCursos()) {
            // size(0) tambien podemos usarlo
            if (curso.getDocentes().isEmpty())
                cursoSinDocentes.add(curso);
        }
        return cursoSinDocentes;
    }

}
