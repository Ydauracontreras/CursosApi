package ar.com.ada.api.cursos.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.cursos.entities.Docente;
import ar.com.ada.api.cursos.entities.Pais.PaisEnum;
import ar.com.ada.api.cursos.entities.Pais.TipoDocuEnum;
import ar.com.ada.api.cursos.repos.DocenteRepository;

@Service
public class DocenteService {

    @Autowired
    DocenteRepository docenteRepository;

    public boolean grabar(Docente docente) {
        docenteRepository.save(docente);
        return true;
    }

    public boolean crearDocente(Docente docente) {
        if (docenteRepository.existsDocente(docente.getPaisId(), docente.getTipoDocumentoId().getValue(),
                docente.getDocumento()))
            return false;
        grabar(docente);
        return true;
    }

    public Docente crearDocente(String nombre, PaisEnum paisId, TipoDocuEnum tipoDocumentoId, String documento,
            Date fechaNacimiento) {
        Docente docente = new Docente();
        docente.setNombre(nombre);
        docente.setPaisId(paisId.getValue());
        docente.setTipoDocumentoId(tipoDocumentoId);
        docente.setDocumento(documento);
        docente.setFechaNacimiento(fechaNacimiento);
        boolean resultado = crearDocente(docente);
        if (resultado)
            return docente;
        else
            return null;

    }

    public Docente buscarPorId(Integer id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if (docente.isPresent())
            return docente.get();
        return null;
    }

    public List<Docente> listartodos() {
        return docenteRepository.findAll();
    }

}