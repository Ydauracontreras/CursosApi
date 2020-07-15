package ar.com.ada.api.cursos.entities;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "docente")
public class Docente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "docente_id")
    private Integer docenteId;
    private List<Curso> cursosQueDicta = new ArrayList<Curso>();

    /**
     * @return the docenteId
     */
    public Integer getDocenteId() {
        return docenteId;
    }

    /**
     * @param docenteId the docenteId to set
     */
    public void setDocenteId(Integer docenteId) {
        this.docenteId = docenteId;
    }

    /**
     * @return the cursos
     */
    public List<Curso> getCursos() {
        return cursosQueDicta;
    }

    /**
     * @param cursos the cursos to set
     */
    public void setCursos(List<Curso> cursosQueDicta) {
        this.cursosQueDicta = cursosQueDicta;
    }

}