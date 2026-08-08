package pe.edu.idat.ms_multas.service;

import pe.edu.idat.ms_multas.model.dto.MultaRequest;
import pe.edu.idat.ms_multas.model.dto.MultaResponse;

import java.util.List;

public interface MultaService {

    List<MultaResponse> listarMultas();

    MultaResponse obtenerMultaPorId(Long id);

    MultaResponse registrarMulta(MultaRequest request);

    MultaResponse obtenerMultaPorPrestamo(Long idPrestamo);

    List<MultaResponse> listarMultasPendientes();

    MultaResponse pagarMulta(Long id);

    void eliminarMulta(Long id);
}