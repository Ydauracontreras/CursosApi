package ar.com.ada.api.cursos.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private Integer cursoId;
    private String nombre;
    @ManyToMany(mappedBy = "cursosQueDicta")
    private List<Docente> docentes = new ArrayList<Docente>();
    @ManyToMany(mappedBy = "cursosQueAsiste")
    private List<Estudiante> estudiates = new ArrayList<Estudiante>();
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Clase> clases = new ArrayList<>();
    @ManyToMany(mappedBy = "cursos")
    private List<Categoria> categorias = new ArrayList<>();
    @OneToMany(mappedBy = "curso")
    private Inscripcion inscripcion;

    /**
     * @return the cursoId
     */
    public Integer getCursoId() {
        return cursoId;
    }

    /**
     * @param cursoId the cursoId to set
     */
    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the docentes
     */
    public List<Docente> getDocentes() {
        return docentes;
    }

    /**
     * @param docentes the docentes to set
     */
    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    /**
     * @return the estudiates
     */
    public List<Estudiante> getEstudiates() {
        return estudiates;
    }

    /**
     * @param estudiates the estudiates to set
     */
    public void setEstudiates(List<Estudiante> estudiates) {
        this.estudiates = estudiates;
    }

    /**
     * @return the clases
     */
    public List<Clase> getClases() {
        return clases;
    }

    /**
     * @param clases the clases to set
     */
    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    /**
     * @return the categorias
     */
    public List<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
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