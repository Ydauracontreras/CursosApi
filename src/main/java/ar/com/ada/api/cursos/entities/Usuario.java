package ar.com.ada.api.cursos.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;
    private String username;
    private String password;
    private String email;
    @Column(name = "fecha_login")
    private Date fechaLogin;
    @Column(name = "tipo_usuario_id")
    private TipoUsuarioEnum tipoUsuarioId;
    @OneToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "estudiante_id")
    private Estudiante estudiante;
    @OneToOne
    @JoinColumn(name = "docente_id", referencedColumnName = "docente_id")
    private Docente docente;
    @OneToMany(mappedBy = "usuario")
    private Inscripcion inscripcion;

    public enum TipoUsuarioEnum {
        DOCENTE(1), ESTUDIANTE(2), STAFF(3);

        private final Integer value;

        private TipoUsuarioEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoUsuarioEnum parse(Integer id) {
            TipoUsuarioEnum status = null;
            for (TipoUsuarioEnum item : TipoUsuarioEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    /**
     * @return the usuarioId
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fechaLogin
     */
    public Date getFechaLogin() {
        return fechaLogin;
    }

    /**
     * @param fechaLogin the fechaLogin to set
     */
    public void setFechaLogin(Date fechaLogin) {
        this.fechaLogin = fechaLogin;
    }

    /**
     * @return the tipoUsuarioId
     */
    public TipoUsuarioEnum getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    /**
     * @param tipoUsuarioId the tipoUsuarioId to set
     */
    public void setTipoUsuarioId(TipoUsuarioEnum tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    /**
     * @return the estudiante
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * @param estudiante the estudiante to set
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * @return the docente
     */
    public Docente getDocente() {
        return docente;
    }

    /**
     * @param docente the docente to set
     */
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    /**
     * @return the inscripcion
     */
    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    /**
     * @param inscripcion the inscripcion to set
     */
    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

}