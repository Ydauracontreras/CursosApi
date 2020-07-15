package ar.com.ada.api.cursos.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "estudiantes")
public class Estudiante extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estudiante_id")
    private Integer estudianteId;
    @ManyToMany
    @JoinTable(name = "estudiante_x_curso", joinColumns = @JoinColumn(name = "estudiate_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursosQueAsiste;

    /**
     * @return the estudianteId
     */
    public Integer getEstudianteId() {
        return estudianteId;
    }

    /**
     * @param estudianteId the estudianteId to set
     */
    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

}