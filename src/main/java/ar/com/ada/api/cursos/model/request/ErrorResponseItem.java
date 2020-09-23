package ar.com.ada.api.cursos.model.request;

public class ErrorResponseItem {

    public ErrorResponseItem(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String field;
    public String message;
}