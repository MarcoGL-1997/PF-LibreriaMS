package pe.edu.idat.ms_multas.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.idat.ms_multas.model.dto.MultaRequest;
import pe.edu.idat.ms_multas.model.dto.MultaResponse;
import pe.edu.idat.ms_multas.model.entity.MultaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MultaMapper {

    @Mapping(target = "idMulta", ignore = true)
    @Mapping(target = "diasRetraso", ignore = true)
    @Mapping(target = "monto", ignore = true)
    @Mapping(target = "fechaGeneracion", ignore = true)
    @Mapping(target = "fechaPago", ignore = true)
    @Mapping(target = "estadoMulta", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    MultaEntity toEntity(MultaRequest request);

    MultaResponse toResponse(MultaEntity entity);

    List<MultaResponse> toResponseList(List<MultaEntity> entities);
}