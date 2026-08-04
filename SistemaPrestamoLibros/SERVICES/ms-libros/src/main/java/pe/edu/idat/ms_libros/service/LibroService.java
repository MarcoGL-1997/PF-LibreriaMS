package pe.edu.idat.ms_libros.service;

import pe.edu.idat.ms_libros.model.dto.LibroRequest;
import pe.edu.idat.ms_libros.model.dto.LibroResponse;

import java.util.List;

public interface LibroService {

    List<LibroResponse> listarLibros();

    LibroResponse obtenerLibroPorId(Long id);

    LibroResponse registrarLibro(LibroRequest request);

    LibroResponse actualizarLibro(Long id, LibroRequest request);

    void eliminarLibro(Long id);

}