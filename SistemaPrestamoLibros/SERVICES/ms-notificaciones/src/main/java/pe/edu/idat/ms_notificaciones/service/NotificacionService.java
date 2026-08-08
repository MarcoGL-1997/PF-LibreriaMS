package pe.edu.idat.ms_notificaciones.service;

import pe.edu.idat.ms_notificaciones.model.dto.NotificacionRequest;
import pe.edu.idat.ms_notificaciones.model.dto.NotificacionResponse;

import java.util.List;

public interface NotificacionService {

    List<NotificacionResponse> listarNotificaciones();

    NotificacionResponse obtenerNotificacionPorId(Long id);

    NotificacionResponse registrarNotificacion(
            NotificacionRequest request
    );

    List<NotificacionResponse> listarPorUsuario(
            Long idUsuario
    );

    List<NotificacionResponse> listarNoLeidas();

    NotificacionResponse marcarComoLeida(
            Long id
    );

    void eliminarNotificacion(Long id);

}