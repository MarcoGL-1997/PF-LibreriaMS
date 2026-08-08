package pe.edu.idat.ms_notificaciones.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
public class NotificacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Long idNotificacion;

    @NotNull(message = "El ID del usuario es obligatorio.")
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @NotBlank(message = "El asunto es obligatorio.")
    @Size(max = 150)
    @Column(name = "asunto", nullable = false, length = 150)
    private String asunto;

    @NotBlank(message = "El mensaje es obligatorio.")
    @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @NotBlank(message = "El tipo es obligatorio.")
    @Size(max = 30)
    @Column(name = "tipo", nullable = false, length = 30)
    private String tipo;

    @Column(name = "leido", nullable = false)
    private Boolean leido;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    public NotificacionEntity() {
    }

    public NotificacionEntity(Long idNotificacion,
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