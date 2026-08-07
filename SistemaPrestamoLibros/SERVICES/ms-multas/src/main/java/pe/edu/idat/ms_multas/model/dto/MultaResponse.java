package pe.edu.idat.ms_multas.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MultaResponse {

    private Long idMulta;
    private Long idPrestamo;
    private Integer diasRetraso;
    private BigDecimal monto;
    private LocalDate fechaGeneracion;
    private LocalDate fechaPago;
    private String estadoMulta;
    private Boolean estado;
    private LocalDateTime fechaRegistro;

    public MultaResponse() {
    }

    public MultaResponse(Long idMulta,
                         Long idPrestamo,
                         Integer diasRetraso,
                         BigDecimal monto,
                         LocalDate fechaGeneracion,
                         LocalDate fechaPago,
                         String estadoMulta,
                         Boolean estado,
                         LocalDateTime fechaRegistro) {

        this.idMulta = idMulta;
        this.idPrestamo = idPrestamo;
        this.diasRetraso = diasRetraso;
        this.monto = monto;
        this.fechaGeneracion = fechaGeneracion;
        this.fechaPago = fechaPago;
        this.estadoMulta = estadoMulta;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(Long idMulta) {
        this.idMulta = idMulta;
    }

    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getDiasRetraso() {
        return diasRetraso;
    }

    public void setDiasRetraso(Integer diasRetraso) {
        this.diasRetraso = diasRetraso;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstadoMulta() {
        return estadoMulta;
    }

    public void setEstadoMulta(String estadoMulta) {
        this.estadoMulta = estadoMulta;
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