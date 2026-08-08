package pe.edu.idat.ms_multas.model.dto;

import jakarta.validation.constraints.NotNull;

public class MultaRequest {

    @NotNull(message = "El ID del préstamo es obligatorio.")
    private Long idPrestamo;

    public MultaRequest() {
    }

    public MultaRequest(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
}