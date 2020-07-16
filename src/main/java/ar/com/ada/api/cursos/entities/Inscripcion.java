package ar.com.ada.api.cursos.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "inscripcion")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Inscripcion_id")
    private Integer inscripcionId;
    private Date fecha;
    // ManyToOne + JoinColumn + name de la columna de referencia
    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "curso_id")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;
    @Column(name = "estado_inscripcion_id")
    private EstadioDeInscripcion estadoInscripcion;

    public enum EstadioDeInscripcion {
        INACTIV(0), ACTIVO(1);

        private final Integer value;

        private EstadioDeInscripcion(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static EstadioDeInscripcion parse(Integer id) {
            EstadioDeInscripcion status = null;
            for (EstadioDeInscripcion item : EstadioDeInscripcion.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    /**
     * @return the inscripcionId
     */
    public Integer getInscripcionId() {
        return inscripcionId;
    }

    /**
     * @param inscripcionId the inscripcionId to set
     */
    public void setInscripcionId(Integer inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the estadoInscripcion
     */
    public EstadioDeInscripcion getEstadoInscripcion() {
        return estadoInscripcion;
    }

    /**
     * @param estadoInscripcion the estadoInscripcion to set
     */
    public void setEstadoInscripcion(EstadioDeInscripcion estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }
}