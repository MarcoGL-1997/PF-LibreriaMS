package pe.edu.idat.ms_notificaciones.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.idat.ms_notificaciones.model.dto.NotificacionRequest;
import pe.edu.idat.ms_notificaciones.model.dto.NotificacionResponse;
import pe.edu.idat.ms_notificaciones.model.entity.NotificacionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificacionMapper {

    @Mapping(target = "idNotificacion", ignore = true)
    @Mapping(target = "leido", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    NotificacionEntity toEntity(NotificacionRequest request);

    NotificacionResponse toResponse(NotificacionEntity entity);

    List<NotificacionResponse> toResponseList(List<NotificacionEntity> entities);

}