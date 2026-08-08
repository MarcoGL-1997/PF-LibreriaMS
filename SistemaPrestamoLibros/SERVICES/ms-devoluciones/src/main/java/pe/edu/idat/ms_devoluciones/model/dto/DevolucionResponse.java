package pe.edu.idat.ms_devoluciones.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DevolucionResponse {

    private Long idDevolucion;
    private Long idPrestamo;
    private LocalDate fechaDevolucion;
    private String estadoLibro;
    private String observacion;
    private Boolean estado;
    private LocalDateTime fechaRegistro;

    public DevolucionResponse() {
    }

    public DevolucionResponse(Long idDevolucion,
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