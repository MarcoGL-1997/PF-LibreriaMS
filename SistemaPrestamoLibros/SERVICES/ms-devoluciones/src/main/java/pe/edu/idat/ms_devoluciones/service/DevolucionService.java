package pe.edu.idat.ms_devoluciones.service;

import pe.edu.idat.ms_devoluciones.model.dto.DevolucionRequest;
import pe.edu.idat.ms_devoluciones.model.dto.DevolucionResponse;

import java.util.List;

public interface DevolucionService {

    List<DevolucionResponse> listarDevoluciones();

    DevolucionResponse obtenerDevolucionPorId(Long id);

    DevolucionResponse registrarDevolucion(
            DevolucionRequest request
    );

    DevolucionResponse obtenerDevolucionPorPrestamo(
            Long idPrestamo
    );

    void eliminarDevolucion(Long id);

}