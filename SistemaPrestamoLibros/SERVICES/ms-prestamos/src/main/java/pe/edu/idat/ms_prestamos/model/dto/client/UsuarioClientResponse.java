package pe.edu.idat.ms_prestamos.model.dto.client;

public class UsuarioClientResponse {

    private Long idUsuario;
    private Boolean estado;

    public UsuarioClientResponse() {
    }

    public UsuarioClientResponse(Long idUsuario, Boolean estado) {
        this.idUsuario = idUsuario;
        this.estado = estado;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}