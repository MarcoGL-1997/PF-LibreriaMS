package pe.edu.idat.ms_multas.service.impl;

import feign.FeignException;
import org.springframework.stereotype.Service;
import pe.edu.idat.ms_multas.client.PrestamoClient;
import pe.edu.idat.ms_multas.exception.BusinessRuleException;
import pe.edu.idat.ms_multas.exception.DuplicateResourceException;
import pe.edu.idat.ms_multas.exception.ResourceNotFoundException;
import pe.edu.idat.ms_multas.model.dto.MultaRequest;
import pe.edu.idat.ms_multas.model.dto.MultaResponse;
import pe.edu.idat.ms_multas.model.dto.client.PrestamoClientResponse;
import pe.edu.idat.ms_multas.model.entity.MultaEntity;
import pe.edu.idat.ms_multas.model.mapper.MultaMapper;
import pe.edu.idat.ms_multas.repository.MultaRepository;
import pe.edu.idat.ms_multas.service.MultaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MultaServiceImpl implements MultaService {

    private static final String ESTADO_PENDIENTE = "PENDIENTE";
    private static final String ESTADO_PAGADA = "PAGADA";

    private static final BigDecimal MONTO_POR_DIA =
            new BigDecimal("2.00");

    private final MultaRepository multaRepository;
    private final MultaMapper multaMapper;
    private final PrestamoClient prestamoClient;

    public MultaServiceImpl(MultaRepository multaRepository,
                            MultaMapper multaMapper,
                            PrestamoClient prestamoClient) {

        this.multaRepository = multaRepository;
        this.multaMapper = multaMapper;
        this.prestamoClient = prestamoClient;
    }

    @Override
    public List<MultaResponse> listarMultas() {

        List<MultaEntity> multas =
                multaRepository.findByEstadoTrue();

        return multaMapper.toResponseList(multas);
    }

    @Override
    public MultaResponse obtenerMultaPorId(Long id) {

        MultaEntity multa = obtenerEntidad(id);

        return multaMapper.toResponse(multa);
    }

    @Override
    public MultaResponse registrarMulta(MultaRequest request) {

        PrestamoClientResponse prestamo =
                validarPrestamo(request.getIdPrestamo());

        boolean multaExistente =
                multaRepository.existsByIdPrestamoAndEstadoTrue(
                        request.getIdPrestamo()
                );

        if (multaExistente) {
            throw new DuplicateResourceException(
                    "El préstamo ya tiene una multa registrada."
            );
        }

        LocalDate fechaActual = LocalDate.now();

        if (prestamo.getFechaVencimiento() == null) {
            throw new BusinessRuleException(
                    "El préstamo no tiene una fecha de vencimiento válida."
            );
        }

        if (!prestamo.getFechaVencimiento().isBefore(fechaActual)) {
            throw new BusinessRuleException(
                    "El préstamo todavía no se encuentra vencido."
            );
        }

        long diasRetrasoCalculados =
                ChronoUnit.DAYS.between(
                        prestamo.getFechaVencimiento(),
                        fechaActual
                );

        int diasRetraso =
                Math.toIntExact(diasRetrasoCalculados);

        BigDecimal monto =
                MONTO_POR_DIA.multiply(
                        BigDecimal.valueOf(diasRetraso)
                );

        MultaEntity multa =
                multaMapper.toEntity(request);

        multa.setDiasRetraso(diasRetraso);
        multa.setMonto(monto);
        multa.setFechaGeneracion(fechaActual);
        multa.setFechaPago(null);
        multa.setEstadoMulta(ESTADO_PENDIENTE);
        multa.setEstado(true);
        multa.setFechaRegistro(LocalDateTime.now());

        MultaEntity multaGuardada =
                multaRepository.save(multa);

        return multaMapper.toResponse(multaGuardada);
    }

    @Override
    public MultaResponse obtenerMultaPorPrestamo(Long idPrestamo) {

        MultaEntity multa =
                multaRepository
                        .findByIdPrestamoAndEstadoTrue(idPrestamo)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "No se encontró una multa para el préstamo con ID: "
                                                + idPrestamo
                                )
                        );

        return multaMapper.toResponse(multa);
    }

    @Override
    public List<MultaResponse> listarMultasPendientes() {

        List<MultaEntity> multas =
                multaRepository
                        .findByEstadoMultaAndEstadoTrue(
                                ESTADO_PENDIENTE
                        );

        return multaMapper.toResponseList(multas);
    }

    @Override
    public MultaResponse pagarMulta(Long id) {

        MultaEntity multa =
                obtenerEntidad(id);

        if (ESTADO_PAGADA.equals(
                multa.getEstadoMulta())) {

            throw new BusinessRuleException(
                    "La multa ya se encuentra pagada."
            );
        }

        multa.setEstadoMulta(
                ESTADO_PAGADA
        );

        multa.setFechaPago(
                LocalDate.now()
        );

        MultaEntity multaActualizada =
                multaRepository.save(multa);

        return multaMapper.toResponse(
                multaActualizada
        );
    }

    @Override
    public void eliminarMulta(Long id) {

        MultaEntity multa =
                obtenerEntidad(id);

        multa.setEstado(false);

        multaRepository.save(multa);
    }

    private MultaEntity obtenerEntidad(Long id) {

        return multaRepository
                .findByIdMultaAndEstadoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Multa no encontrada con ID: " + id
                        )
                );
    }

    private PrestamoClientResponse validarPrestamo(Long idPrestamo) {

        try {

            PrestamoClientResponse prestamo =
                    prestamoClient.obtenerPrestamoPorId(
                            idPrestamo
                    );

            if (!Boolean.TRUE.equals(
                    prestamo.getEstado())) {

                throw new BusinessRuleException(
                        "El préstamo no se encuentra activo."
                );
            }

            return prestamo;

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(
                    "Préstamo no encontrado con ID: "
                            + idPrestamo
            );
        }
    }
}