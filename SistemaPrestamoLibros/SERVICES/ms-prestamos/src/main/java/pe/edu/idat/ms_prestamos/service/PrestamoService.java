package pe.edu.idat.ms_prestamos.service;

import pe.edu.idat.ms_prestamos.model.dto.PrestamoRequest;
import pe.edu.idat.ms_prestamos.model.dto.PrestamoResponse;

import java.util.List;

public interface PrestamoService {

    List<PrestamoResponse> listarPrestamos();

    PrestamoResponse obtenerPrestamoPorId(Long id);

    PrestamoResponse registrarPrestamo(
            PrestamoRequest request
    );

    List<PrestamoResponse> listarPrestamosPorUsuario(
            Long idUsuario
    );

    List<PrestamoResponse> listarPrestamosActivos();

    List<PrestamoResponse> listarPrestamosVencidos();

    PrestamoResponse finalizarPrestamo(Long id);

    void eliminarPrestamo(Long id);

}