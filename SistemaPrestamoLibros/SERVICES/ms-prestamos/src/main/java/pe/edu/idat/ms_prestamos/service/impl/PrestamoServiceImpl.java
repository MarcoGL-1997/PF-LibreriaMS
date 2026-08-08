package pe.edu.idat.ms_prestamos.service.impl;

import feign.FeignException;
import org.springframework.stereotype.Service;
import pe.edu.idat.ms_prestamos.client.LibroClient;
import pe.edu.idat.ms_prestamos.client.UsuarioClient;
import pe.edu.idat.ms_prestamos.exception.BusinessRuleException;
import pe.edu.idat.ms_prestamos.exception.DuplicateResourceException;
import pe.edu.idat.ms_prestamos.exception.ResourceNotFoundException;
import pe.edu.idat.ms_prestamos.model.dto.PrestamoRequest;
import pe.edu.idat.ms_prestamos.model.dto.PrestamoResponse;
import pe.edu.idat.ms_prestamos.model.dto.client.LibroClientResponse;
import pe.edu.idat.ms_prestamos.model.dto.client.UsuarioClientResponse;
import pe.edu.idat.ms_prestamos.model.entity.PrestamoEntity;
import pe.edu.idat.ms_prestamos.model.mapper.PrestamoMapper;
import pe.edu.idat.ms_prestamos.repository.PrestamoRepository;
import pe.edu.idat.ms_prestamos.service.PrestamoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private static final String ESTADO_ACTIVO = "ACTIVO";
    private static final String ESTADO_FINALIZADO = "FINALIZADO";

    private final PrestamoRepository prestamoRepository;
    private final PrestamoMapper prestamoMapper;
    private final UsuarioClient usuarioClient;
    private final LibroClient libroClient;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,
                               PrestamoMapper prestamoMapper,
                               UsuarioClient usuarioClient,
                               LibroClient libroClient) {

        this.prestamoRepository = prestamoRepository;
        this.prestamoMapper = prestamoMapper;
        this.usuarioClient = usuarioClient;
        this.libroClient = libroClient;
    }

    @Override
    public List<PrestamoResponse> listarPrestamos() {

        List<PrestamoEntity> prestamos =
                prestamoRepository.findByEstadoTrue();

        return prestamoMapper.toResponseList(prestamos);
    }

    @Override
    public PrestamoResponse obtenerPrestamoPorId(Long id) {

        PrestamoEntity prestamo = obtenerEntidad(id);

        return prestamoMapper.toResponse(prestamo);
    }

    @Override
    public PrestamoResponse registrarPrestamo(PrestamoRequest request) {

        validarUsuario(request.getIdUsuario());

        validarLibro(request.getIdLibro());

        boolean prestamoActivoExistente =
                prestamoRepository
                        .existsByIdUsuarioAndIdLibroAndEstadoPrestamoAndEstadoTrue(
                                request.getIdUsuario(),
                                request.getIdLibro(),
                                ESTADO_ACTIVO
                        );

        if (prestamoActivoExistente) {

            throw new DuplicateResourceException(
                    "El usuario ya tiene un préstamo activo de este libro."
            );
        }

        PrestamoEntity prestamo =
                prestamoMapper.toEntity(request);

        LocalDate fechaActual = LocalDate.now();

        prestamo.setFechaPrestamo(fechaActual);

        prestamo.setFechaVencimiento(
                fechaActual.plusDays(7)
        );

        prestamo.setEstadoPrestamo(
                ESTADO_ACTIVO
        );

        prestamo.setEstado(true);

        prestamo.setFechaRegistro(
                LocalDateTime.now()
        );

        PrestamoEntity prestamoGuardado =
                prestamoRepository.save(prestamo);

        return prestamoMapper.toResponse(
                prestamoGuardado
        );
    }

    @Override
    public List<PrestamoResponse> listarPrestamosPorUsuario(
            Long idUsuario) {

        validarUsuario(idUsuario);

        List<PrestamoEntity> prestamos =
                prestamoRepository
                        .findByIdUsuarioAndEstadoTrue(idUsuario);

        return prestamoMapper.toResponseList(
                prestamos
        );
    }

    @Override
    public List<PrestamoResponse> listarPrestamosActivos() {

        List<PrestamoEntity> prestamos =
                prestamoRepository
                        .findByEstadoPrestamoAndEstadoTrue(
                                ESTADO_ACTIVO
                        );

        return prestamoMapper.toResponseList(
                prestamos
        );
    }

    @Override
    public List<PrestamoResponse> listarPrestamosVencidos() {

        List<PrestamoEntity> prestamos =
                prestamoRepository
                        .findByFechaVencimientoBeforeAndEstadoPrestamoAndEstadoTrue(
                                LocalDate.now(),
                                ESTADO_ACTIVO
                        );

        return prestamoMapper.toResponseList(
                prestamos
        );
    }

    @Override
    public PrestamoResponse finalizarPrestamo(Long id) {

        PrestamoEntity prestamo =
                obtenerEntidad(id);

        if (ESTADO_FINALIZADO.equals(
                prestamo.getEstadoPrestamo())) {

            throw new BusinessRuleException(
                    "El préstamo ya se encuentra finalizado."
            );
        }

        prestamo.setEstadoPrestamo(
                ESTADO_FINALIZADO
        );

        PrestamoEntity prestamoActualizado =
                prestamoRepository.save(prestamo);

        return prestamoMapper.toResponse(
                prestamoActualizado
        );
    }

    @Override
    public void eliminarPrestamo(Long id) {

        PrestamoEntity prestamo =
                obtenerEntidad(id);

        prestamo.setEstado(false);

        prestamoRepository.save(prestamo);
    }

    private PrestamoEntity obtenerEntidad(Long id) {

        return prestamoRepository
                .findByIdPrestamoAndEstadoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Préstamo no encontrado con ID: " + id
                        )
                );
    }

    private void validarUsuario(Long idUsuario) {

        try {

            UsuarioClientResponse usuario =
                    usuarioClient.obtenerUsuarioPorId(idUsuario);

            if (!Boolean.TRUE.equals(usuario.getEstado())) {

                throw new BusinessRuleException(
                        "El usuario no se encuentra activo."
                );
            }

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(
                    "Usuario no encontrado con ID: "
                            + idUsuario
            );
        }
    }

    private void validarLibro(Long idLibro) {

        try {

            LibroClientResponse libro =
                    libroClient.obtenerLibroPorId(idLibro);

            if (!Boolean.TRUE.equals(libro.getEstado())) {

                throw new BusinessRuleException(
                        "El libro no se encuentra activo."
                );
            }

            if (libro.getCantidadDisponible() == null
                    || libro.getCantidadDisponible() <= 0) {

                throw new BusinessRuleException(
                        "El libro no tiene ejemplares disponibles."
                );
            }

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(
                    "Libro no encontrado con ID: "
                            + idLibro
            );
        }
    }

}