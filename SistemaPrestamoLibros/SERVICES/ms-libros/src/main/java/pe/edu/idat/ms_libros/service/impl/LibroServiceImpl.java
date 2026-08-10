package pe.edu.idat.ms_libros.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.idat.ms_libros.exception.DuplicateResourceException;
import pe.edu.idat.ms_libros.exception.ResourceNotFoundException;
import pe.edu.idat.ms_libros.model.dto.LibroRequest;
import pe.edu.idat.ms_libros.model.dto.LibroResponse;
import pe.edu.idat.ms_libros.model.entity.LibroEntity;
import pe.edu.idat.ms_libros.model.mapper.LibroMapper;
import pe.edu.idat.ms_libros.repository.LibroRepository;
import pe.edu.idat.ms_libros.service.LibroService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final LibroMapper libroMapper;

    public LibroServiceImpl(LibroRepository libroRepository,
                            LibroMapper libroMapper) {
        this.libroRepository = libroRepository;
        this.libroMapper = libroMapper;
    }

    @Override
    public List<LibroResponse> listarLibros() {

        List<LibroEntity> libros = libroRepository.findByEstadoTrue();

        return libroMapper.toResponseList(libros);
    }

    @Override
    public LibroResponse obtenerLibroPorId(Long id) {

        LibroEntity libro = obtenerEntidad(id);

        return libroMapper.toResponse(libro);
    }

    @Override
    public LibroResponse registrarLibro(LibroRequest request) {

        if (libroRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateResourceException("El ISBN ya se encuentra registrado.");
        }

        LibroEntity libro = libroMapper.toEntity(request);

        libro.setEstado(true);

        libro.setFechaRegistro(LocalDateTime.now());

        libro.setCantidadDisponible(libro.getCantidadTotal());

        LibroEntity libroGuardado = libroRepository.save(libro);

        return libroMapper.toResponse(libroGuardado);
    }

    @Override
    public LibroResponse actualizarLibro(Long id, LibroRequest request) {

        LibroEntity libro = obtenerEntidad(id);

        if (!libro.getIsbn().equals(request.getIsbn())
                && libroRepository.existsByIsbn(request.getIsbn())) {

            throw new DuplicateResourceException("El ISBN ya se encuentra registrado.");
        }

        libro.setIsbn(request.getIsbn());
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        libro.setEditorial(request.getEditorial());
        libro.setCategoria(request.getCategoria());
        libro.setAnioPublicacion(request.getAnioPublicacion());
        libro.setCantidadTotal(request.getCantidadTotal());

        /*
         * Si la nueva cantidad total es menor que la cantidad disponible,
         * se ajusta automáticamente para evitar inconsistencias.
         */
        if (libro.getCantidadDisponible() > libro.getCantidadTotal()) {
            libro.setCantidadDisponible(libro.getCantidadTotal());
        }

        libro.setUbicacion(request.getUbicacion());

        LibroEntity libroActualizado = libroRepository.save(libro);

        return libroMapper.toResponse(libroActualizado);
    }

    @Override
    public void eliminarLibro(Long id) {

        LibroEntity libro = obtenerEntidad(id);

        libro.setEstado(false);

        libroRepository.save(libro);
    }

    private LibroEntity obtenerEntidad(Long id) {

return libroRepository.findByIdLibroAndEstadoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Libro no encontrado con ID: " + id));
    }

    @Override
public void disminuirDisponibilidad(Long id) {

    LibroEntity libro = obtenerEntidad(id);

    if (libro.getCantidadDisponible() == null
            || libro.getCantidadDisponible() <= 0) {

        throw new IllegalStateException(
                "El libro no tiene ejemplares disponibles."
        );
    }

    libro.setCantidadDisponible(
            libro.getCantidadDisponible() - 1
    );

    libroRepository.save(libro);
    }

    @Override
    public void aumentarDisponibilidad(Long id) {

        LibroEntity libro = obtenerEntidad(id);

        if (libro.getCantidadDisponible() == null) {
            libro.setCantidadDisponible(1);
        } else if (libro.getCantidadDisponible() < libro.getCantidadTotal()) {
            libro.setCantidadDisponible(
                    libro.getCantidadDisponible() + 1
            );
        }

        libroRepository.save(libro);
    }

}