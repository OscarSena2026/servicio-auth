package co.edu.sena.auth.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO (objeto de transferencia de datos): es el "sobre" con los datos
 * que el cliente envia en el body de la peticion (en formato JSON).
 */
public class CredencialesDTO {

    // No puede llegar vacio ni nulo
    @NotBlank(message = "El usuario es obligatorio")
    private String usuario;

    // No puede llegar vacio ni nulo
    @NotBlank(message = "La contrasena es obligatoria")
    private String contrasena;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
