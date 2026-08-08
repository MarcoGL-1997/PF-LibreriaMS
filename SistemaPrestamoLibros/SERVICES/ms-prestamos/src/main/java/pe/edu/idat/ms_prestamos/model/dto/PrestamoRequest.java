package pe.edu.idat.ms_prestamos.model.dto;

import jakarta.validation.constraints.NotNull;

public class PrestamoRequest {

    @NotNull(message = "El ID del usuario es obligatorio.")
    private Long idUsuario;

    @NotNull(message = "El ID del libro es obligatorio.")
    private Long idLibro;

    public PrestamoRequest() {
    }

    public PrestamoRequest(Long idUsuario, Long idLibro) {
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

}