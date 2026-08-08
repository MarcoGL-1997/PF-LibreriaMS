package pe.edu.idat.ms_notificaciones.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NotificacionRequest {

    @NotNull(message = "El ID del usuario es obligatorio.")
    private Long idUsuario;

    @NotBlank(message = "El asunto es obligatorio.")
    @Size(max = 150)
    private String asunto;

    @NotBlank(message = "El mensaje es obligatorio.")
    private String mensaje;

    @NotBlank(message = "El tipo es obligatorio.")
    @Size(max = 30)
    private String tipo;

    public NotificacionRequest() {
    }

    public NotificacionRequest(Long idUsuario,
                               String asunto,
                               String mensaje,
                               String tipo) {

        this.idUsuario = idUsuario;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}