package pe.edu.idat.ms_devoluciones.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "devoluciones")
public class DevolucionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devolucion")
    private Long idDevolucion;

    @NotNull(message = "El ID del préstamo es obligatorio.")
    @Column(name = "id_prestamo", nullable = false)
    private Long idPrestamo;

    @NotNull(message = "La fecha de devolución es obligatoria.")
    @Column(name = "fecha_devolucion", nullable = false)
    private LocalDate fechaDevolucion;

    @NotBlank(message = "El estado del libro es obligatorio.")
    @Size(max = 30)
    @Column(name = "estado_libro", nullable = false, length = 30)
    private String estadoLibro;

    @Size(max = 250)
    @Column(name = "observacion", length = 250)
    private String observacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    public DevolucionEntity() {
    }

    public DevolucionEntity(Long idDevolucion,
                            Long idPrestamo,
                            LocalDate fechaDevolucion,
                            String estadoLibro,
                            String observacion,
                            Boolean estado,
                            LocalDateTime fechaRegistro) {

        this.idDevolucion = idDevolucion;
        this.idPrestamo = idPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoLibro = estadoLibro;
        this.observacion = observacion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(Long idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstadoLibro() {
        return estadoLibro;
    }

    public void setEstadoLibro(String estadoLibro) {
        this.estadoLibro = estadoLibro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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