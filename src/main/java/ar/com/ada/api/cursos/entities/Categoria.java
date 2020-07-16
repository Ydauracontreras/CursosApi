package ar.com.ada.api.cursos.entities;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Integer categoriaId;

    private String nombre;
    private String descripcion;

    @ManyToMany
    @JoinTable(name = "curso_x_categoria", joinColumns = @JoinColumn(name = "categoria_id"), inverseJoinColumns = @JoinColumn(name = "estudiante_id"))
    private List<Curso> cursos = new ArrayList<>();

    /**
     * @return the categoriaId
     */
    public Integer getCategoriaId() {
        return categoriaId;
    }

    /**
     * @param categoriaId the categoriaId to set
     */
    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the cursos
     */
    public List<Curso> getCursos() {
        return cursos;
    }

    /**
     * @param cursos the cursos to set
     */
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

}