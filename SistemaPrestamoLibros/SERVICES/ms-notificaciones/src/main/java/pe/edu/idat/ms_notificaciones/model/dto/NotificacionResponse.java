package pe.edu.idat.ms_notificaciones.model.dto;

import java.time.LocalDateTime;

public class NotificacionResponse {

    private Long idNotificacion;
    private Long idUsuario;
    private String asunto;
    private String mensaje;
    private String tipo;
    private Boolean leido;
    private Boolean estado;
    private LocalDateTime fechaRegistro;

    public NotificacionResponse() {
    }

    public NotificacionResponse(Long idNotificacion,
                                Long idUsuario,
                                String asunto,
                                String mensaje,
                                String tipo,
                                Boolean leido,
                                Boolean estado,
                                LocalDateTime fechaRegistro) {

        this.idNotificacion = idNotificacion;
        this.idUsuario = idUsuario;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.leido = leido;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
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

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}