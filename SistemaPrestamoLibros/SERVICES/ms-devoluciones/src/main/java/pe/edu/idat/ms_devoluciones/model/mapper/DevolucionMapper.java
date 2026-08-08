package pe.edu.idat.ms_devoluciones.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.idat.ms_devoluciones.model.dto.DevolucionRequest;
import pe.edu.idat.ms_devoluciones.model.dto.DevolucionResponse;
import pe.edu.idat.ms_devoluciones.model.entity.DevolucionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DevolucionMapper {

    @Mapping(target = "idDevolucion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    DevolucionEntity toEntity(DevolucionRequest request);

    DevolucionResponse toResponse(DevolucionEntity entity);

    List<DevolucionResponse> toResponseList(List<DevolucionEntity> entities);

}