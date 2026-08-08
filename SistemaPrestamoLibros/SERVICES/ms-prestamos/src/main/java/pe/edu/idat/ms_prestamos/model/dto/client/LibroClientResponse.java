package pe.edu.idat.ms_prestamos.model.dto.client;

public class LibroClientResponse {

    private Long idLibro;
    private Integer cantidadDisponible;
    private Boolean estado;

    public LibroClientResponse() {
    }

    public LibroClientResponse(Long idLibro,
                               Integer cantidadDisponible,
                               Boolean estado) {

        this.idLibro = idLibro;
        this.cantidadDisponible = cantidadDisponible;
        this.estado = estado;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}