package ar.com.ada.api.cursos.model.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "username es obligatorio")
    public String username;
    @NotBlank(message = "password es obligatorio")
    public String password;
}
