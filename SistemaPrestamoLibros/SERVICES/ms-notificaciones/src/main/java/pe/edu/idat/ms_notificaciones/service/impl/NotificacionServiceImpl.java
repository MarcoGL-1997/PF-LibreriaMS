package pe.edu.idat.ms_notificaciones.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.idat.ms_notificaciones.exception.ResourceNotFoundException;
import pe.edu.idat.ms_notificaciones.model.dto.NotificacionRequest;
import pe.edu.idat.ms_notificaciones.model.dto.NotificacionResponse;
import pe.edu.idat.ms_notificaciones.model.entity.NotificacionEntity;
import pe.edu.idat.ms_notificaciones.model.mapper.NotificacionMapper;
import pe.edu.idat.ms_notificaciones.repository.NotificacionRepository;
import pe.edu.idat.ms_notificaciones.service.NotificacionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final NotificacionMapper notificacionMapper;

    public NotificacionServiceImpl(
            NotificacionRepository notificacionRepository,
            NotificacionMapper notificacionMapper) {

        this.notificacionRepository = notificacionRepository;
        this.notificacionMapper = notificacionMapper;
    }

    @Override
    public List<NotificacionResponse> listarNotificaciones() {

        List<NotificacionEntity> notificaciones =
                notificacionRepository.findByEstadoTrue();

        return notificacionMapper.toResponseList(notificaciones);
    }

    @Override
    public NotificacionResponse obtenerNotificacionPorId(Long id) {

        NotificacionEntity notificacion =
                obtenerEntidad(id);

        return notificacionMapper.toResponse(notificacion);
    }

    @Override
    public NotificacionResponse registrarNotificacion(
            NotificacionRequest request) {

        NotificacionEntity notificacion =
                notificacionMapper.toEntity(request);

        notificacion.setLeido(false);
        notificacion.setEstado(true);
        notificacion.setFechaRegistro(LocalDateTime.now());

        NotificacionEntity guardada =
                notificacionRepository.save(notificacion);

        return notificacionMapper.toResponse(guardada);
    }

    @Override
    public List<NotificacionResponse> listarPorUsuario(
            Long idUsuario) {

        List<NotificacionEntity> notificaciones =
                notificacionRepository
                        .findByIdUsuarioAndEstadoTrue(idUsuario);

        return notificacionMapper.toResponseList(notificaciones);
    }

    @Override
    public List<NotificacionResponse> listarNoLeidas() {

        List<NotificacionEntity> notificaciones =
                notificacionRepository
                        .findByLeidoFalseAndEstadoTrue();

        return notificacionMapper.toResponseList(notificaciones);
    }

    @Override
    public NotificacionResponse marcarComoLeida(Long id) {

        NotificacionEntity notificacion =
                obtenerEntidad(id);

        notificacion.setLeido(true);

        NotificacionEntity actualizada =
                notificacionRepository.save(notificacion);

        return notificacionMapper.toResponse(actualizada);
    }

    @Override
    public void eliminarNotificacion(Long id) {

        NotificacionEntity notificacion =
                obtenerEntidad(id);

        notificacion.setEstado(false);

        notificacionRepository.save(notificacion);
    }

    private NotificacionEntity obtenerEntidad(Long id) {

        return notificacionRepository
                .findByIdNotificacionAndEstadoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Notificación no encontrada con ID: " + id
                        ));
    }

}