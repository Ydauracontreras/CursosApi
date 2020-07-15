package ar.com.ada.api.cursos.entities;

public class Pais {

    public enum PaisEnum {
        Argentina(32), Venezuela(840), Estado_Unidos(862);

        private final Integer value;

        private PaisEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static PaisEnum parse(Integer id) {
            PaisEnum status = null;
            for (PaisEnum item : PaisEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    public enum TipoDocumentoEnum {
        Dni(1), Pasaporte(2);

        private final Integer value;

        private TipoDocumentoEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoDocumentoEnum parse(Integer id) {
            TipoDocumentoEnum status = null;
            for (TipoDocumentoEnum item : TipoDocumentoEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

}
