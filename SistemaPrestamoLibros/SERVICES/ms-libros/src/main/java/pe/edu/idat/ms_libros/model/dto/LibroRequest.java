package pe.edu.idat.ms_libros.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LibroRequest {

    @NotBlank(message = "El ISBN es obligatorio.")
    @Size(max = 20)
    private String isbn;

    @NotBlank(message = "El título es obligatorio.")
    @Size(max = 200)
    private String titulo;

    @NotBlank(message = "El autor es obligatorio.")
    @Size(max = 150)
    private String autor;

    @NotBlank(message = "La editorial es obligatoria.")
    @Size(max = 150)
    private String editorial;

    @NotBlank(message = "La categoría es obligatoria.")
    @Size(max = 100)
    private String categoria;

    @Min(value = 1, message = "El año de publicación debe ser válido.")
    private Integer anioPublicacion;

    @Min(value = 1, message = "La cantidad total debe ser mayor que cero.")
    private Integer cantidadTotal;

    @Size(max = 100)
    private String ubicacion;

    public LibroRequest() {
    }

    public LibroRequest(String isbn, String titulo, String autor,
                        String editorial, String categoria,
                        Integer anioPublicacion,
                        Integer cantidadTotal,
                        String ubicacion) {

        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
        this.anioPublicacion = anioPublicacion;
        this.cantidadTotal = cantidadTotal;
        this.ubicacion = ubicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}