package pe.edu.idat.ms_devoluciones.service.impl;

import feign.FeignException;
import org.springframework.stereotype.Service;
import pe.edu.idat.ms_devoluciones.client.PrestamoClient;
import pe.edu.idat.ms_devoluciones.exception.BusinessRuleException;
import pe.edu.idat.ms_devoluciones.exception.DuplicateResourceException;
import pe.edu.idat.ms_devoluciones.exception.ResourceNotFoundException;
import pe.edu.idat.ms_devoluciones.model.dto.DevolucionRequest;
import pe.edu.idat.ms_devoluciones.model.dto.DevolucionResponse;
import pe.edu.idat.ms_devoluciones.model.dto.client.PrestamoClientResponse;
import pe.edu.idat.ms_devoluciones.model.entity.DevolucionEntity;
import pe.edu.idat.ms_devoluciones.model.mapper.DevolucionMapper;
import pe.edu.idat.ms_devoluciones.repository.DevolucionRepository;
import pe.edu.idat.ms_devoluciones.service.DevolucionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DevolucionServiceImpl implements DevolucionService {

    private static final String ESTADO_ACTIVO = "ACTIVO";
    private static final String ESTADO_FINALIZADO = "FINALIZADO";

    private final DevolucionRepository devolucionRepository;
    private final DevolucionMapper devolucionMapper;
    private final PrestamoClient prestamoClient;

    public DevolucionServiceImpl(
            DevolucionRepository devolucionRepository,
            DevolucionMapper devolucionMapper,
            PrestamoClient prestamoClient) {

        this.devolucionRepository = devolucionRepository;
        this.devolucionMapper = devolucionMapper;
        this.prestamoClient = prestamoClient;
    }

    @Override
    public List<DevolucionResponse> listarDevoluciones() {

        List<DevolucionEntity> devoluciones =
                devolucionRepository.findByEstadoTrue();

        return devolucionMapper.toResponseList(devoluciones);
    }

    @Override
    public DevolucionResponse obtenerDevolucionPorId(Long id) {

        DevolucionEntity devolucion = obtenerEntidad(id);

        return devolucionMapper.toResponse(devolucion);
    }

    @Override
    public DevolucionResponse registrarDevolucion(
            DevolucionRequest request) {

        validarPrestamo(request.getIdPrestamo());

        boolean yaExiste =
                devolucionRepository
                        .findByIdPrestamoAndEstadoTrue(
                                request.getIdPrestamo())
                        .isPresent();

        if (yaExiste) {

            throw new DuplicateResourceException(
                    "El préstamo ya fue devuelto."
            );
        }

        DevolucionEntity devolucion =
                devolucionMapper.toEntity(request);

        devolucion.setEstado(true);

        devolucion.setFechaRegistro(
                LocalDateTime.now()
        );

        DevolucionEntity guardada =
                devolucionRepository.save(devolucion);

        // Finaliza el préstamo en ms-prestamos
        prestamoClient.finalizarPrestamo(
                request.getIdPrestamo()
        );

        return devolucionMapper.toResponse(
                guardada
        );
    }

    @Override
    public DevolucionResponse obtenerDevolucionPorPrestamo(
            Long idPrestamo) {

        DevolucionEntity devolucion =
                devolucionRepository
                        .findByIdPrestamoAndEstadoTrue(idPrestamo)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "No existe devolución para el préstamo: "
                                                + idPrestamo));

        return devolucionMapper.toResponse(
                devolucion
        );
    }

    @Override
    public void eliminarDevolucion(Long id) {

        DevolucionEntity devolucion =
                obtenerEntidad(id);

        devolucion.setEstado(false);

        devolucionRepository.save(devolucion);
    }

    private DevolucionEntity obtenerEntidad(Long id) {

        return devolucionRepository
                .findByIdDevolucionAndEstadoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Devolución no encontrada con ID: " + id
                        ));
    }

    private void validarPrestamo(Long idPrestamo) {

        try {

            PrestamoClientResponse prestamo =
                    prestamoClient.obtenerPrestamoPorId(idPrestamo);

            if (!Boolean.TRUE.equals(prestamo.getEstado())) {

                throw new BusinessRuleException(
                        "El préstamo no se encuentra activo."
                );
            }

            if (!ESTADO_ACTIVO.equals(
                    prestamo.getEstadoPrestamo())) {

                throw new BusinessRuleException(
                        "El préstamo ya fue finalizado."
                );
            }

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(
                    "Préstamo no encontrado con ID: "
                            + idPrestamo
            );
        }
    }

}