package pe.edu.idat.ms_devoluciones.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class DevolucionRequest {

    @NotNull(message = "El ID del préstamo es obligatorio.")
    private Long idPrestamo;

    @NotNull(message = "La fecha de devolución es obligatoria.")
    private LocalDate fechaDevolucion;

    @NotBlank(message = "El estado del libro es obligatorio.")
    @Size(max = 30)
    private String estadoLibro;

    @Size(max = 250)
    private String observacion;

    public DevolucionRequest() {
    }

    public DevolucionRequest(Long idPrestamo,
                             LocalDate fechaDevolucion,
                             String estadoLibro,
                             String observacion) {
        this.idPrestamo = idPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoLibro = estadoLibro;
        this.observacion = observacion;
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
}