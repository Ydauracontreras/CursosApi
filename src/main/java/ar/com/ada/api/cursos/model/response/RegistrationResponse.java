package ar.com.ada.api.cursos.model.response;

import java.util.ArrayList;
import java.util.List;

import ar.com.ada.api.cursos.model.request.ErrorResponseItem;

public class RegistrationResponse {
    public boolean isOk = false;
    public String message = "";
    public Integer userId;

    public List<ErrorResponseItem> errors = new ArrayList<>();
}
