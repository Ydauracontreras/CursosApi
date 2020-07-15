package ar.com.ada.api.cursos.entities;

import javax.persistence.*;

@Entity
@Table(name = "contenido")
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contenidoId")
    private Integer contenidoId;
    private String descripcion;
    @Column(name="descripcion_larga")
    private String descripcionLarga;
    private String payload;
    @Column(name="payload_simple")
    private String payloadSimple;

    @ManyToOne
    @JoinColumn()



    }

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

}

}
