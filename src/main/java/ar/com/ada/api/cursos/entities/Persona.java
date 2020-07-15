package ar.com.ada.api.cursos.entities;

import java.util.Date;

import javax.persistence.*;

import ar.com.ada.api.cursos.entities.Pais.PaisEnum;
import ar.com.ada.api.cursos.entities.Pais.TipoDocumentoEnum;

public class Persona {
    private String nombre;
    @Column(name = "pais_id")
    private PaisEnum paisId;
    @Column(name = "tipo_documento_id")
    private TipoDocumentoEnum tipodocumentoId;
    private String documento;
    @Column(name = "fecha_nacimiento")
    private Date fechadeNacimiento;

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
     * @return the paisId
     */
    public PaisEnum getPaisId() {
        return paisId;
    }

    /**
     * @param paisId the paisId to set
     */
    public void setPaisId(PaisEnum paisId) {
        this.paisId = paisId;
    }

    /**
     * @return the tipodocumentoId
     */
    public TipoDocumentoEnum getTipodocumentoId() {
        return tipodocumentoId;
    }

    /**
     * @param tipodocumentoId the tipodocumentoId to set
     */
    public void setTipodocumentoId(TipoDocumentoEnum tipodocumentoId) {
        this.tipodocumentoId = tipodocumentoId;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the fechadeNacimiento
     */
    public Date getFechadeNacimiento() {
        return fechadeNacimiento;
    }

    /**
     * @param fechadeNacimiento the fechadeNacimiento to set
     */
    public void setFechadeNacimiento(Date fechadeNacimiento) {
        this.fechadeNacimiento = fechadeNacimiento;
    }

}