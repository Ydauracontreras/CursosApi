package ar.com.ada.api.cursos.entities;

import javax.persistence.*;

@Entity
@Table(name = "contenido")
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contenido_id")
    private Integer contenidoId;
    private String descripcion;
    @Column(name = "tipo_contenido_id")
    private TipodeContenidoEnum tipocontenidoId;
    @Column(name = "descripcion_larga")
    private String descripcionLarga;
    private String payload;
    @Column(name = "payload_simple")
    private String payloadSimple;
    @ManyToOne
    @JoinColumn(name = "clase_id", referencedColumnName = "clase_id")
    private Clase clase;

    public enum TipodeContenidoEnum {
        Url(1), Texto(2), Video(3);

        private final Integer value;

        private TipodeContenidoEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipodeContenidoEnum parse(Integer id) {
            TipodeContenidoEnum status = null;
            for (TipodeContenidoEnum item : TipodeContenidoEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }

    }

    /**
     * @return the contenidoId
     */
    public Integer getContenidoId() {
        return contenidoId;
    }

    /**
     * @param contenidoId the contenidoId to set
     */
    public void setContenidoId(Integer contenidoId) {
        this.contenidoId = contenidoId;
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
     * @return the tipocontenidoId
     */
    public TipodeContenidoEnum getTipocontenidoId() {
        return tipocontenidoId;
    }

    /**
     * @param tipocontenidoId the tipocontenidoId to set
     */
    public void setTipocontenidoId(TipodeContenidoEnum tipocontenidoId) {
        this.tipocontenidoId = tipocontenidoId;
    }

    /**
     * @return the descripcionLarga
     */
    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    /**
     * @param descripcionLarga the descripcionLarga to set
     */
    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    /**
     * @return the payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     * @return the payloadSimple
     */
    public String getPayloadSimple() {
        return payloadSimple;
    }

    /**
     * @param payloadSimple the payloadSimple to set
     */
    public void setPayloadSimple(String payloadSimple) {
        this.payloadSimple = payloadSimple;
    }

    /**
     * @return the clase
     */
    public Clase getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(Clase clase) {
        this.clase = clase;
    }
}
