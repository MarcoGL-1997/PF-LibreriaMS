package pe.edu.idat.ms_libros.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "libros")
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long idLibro;

    @NotBlank(message = "El ISBN es obligatorio.")
    @Size(max = 20)
    @Column(name = "isbn", nullable = false, unique = true, length = 20)
    private String isbn;

    @NotBlank(message = "El título es obligatorio.")
    @Size(max = 200)
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @NotBlank(message = "El autor es obligatorio.")
    @Size(max = 150)
    @Column(name = "autor", nullable = false, length = 150)
    private String autor;

    @NotBlank(message = "La editorial es obligatoria.")
    @Size(max = 150)
    @Column(name = "editorial", nullable = false, length = 150)
    private String editorial;

    @NotBlank(message = "La categoría es obligatoria.")
    @Size(max = 100)
    @Column(name = "categoria", nullable = false, length = 100)
    private String categoria;

    @Min(value = 1, message = "El año de publicación debe ser válido.")
    @Column(name = "anio_publicacion", nullable = false)
    private Integer anioPublicacion;

    @Min(value = 1, message = "La cantidad total debe ser mayor que cero.")
    @Column(name = "cantidad_total", nullable = false)
    private Integer cantidadTotal;

    @Min(value = 0, message = "La cantidad disponible no puede ser negativa.")
    @Column(name = "cantidad_disponible", nullable = false)
    private Integer cantidadDisponible;

    @Size(max = 100)
    @Column(name = "ubicacion", length = 100)
    private String ubicacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    public LibroEntity() {
    }

    public LibroEntity(Long idLibro,
                       String isbn,
                       String titulo,
                       String autor,
                       String editorial,
                       String categoria,
                       Integer anioPublicacion,
                       Integer cantidadTotal,
                       Integer cantidadDisponible,
                       String ubicacion,
                       Boolean estado,
                       LocalDateTime fechaRegistro) {

        this.idLibro = idLibro;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
        this.anioPublicacion = anioPublicacion;
        this.cantidadTotal = cantidadTotal;
        this.cantidadDisponible = cantidadDisponible;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
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

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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