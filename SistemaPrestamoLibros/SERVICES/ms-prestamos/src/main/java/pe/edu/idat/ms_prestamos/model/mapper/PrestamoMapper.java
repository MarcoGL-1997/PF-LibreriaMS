package pe.edu.idat.ms_prestamos.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.idat.ms_prestamos.model.dto.PrestamoRequest;
import pe.edu.idat.ms_prestamos.model.dto.PrestamoResponse;
import pe.edu.idat.ms_prestamos.model.entity.PrestamoEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrestamoMapper {

    @Mapping(target = "idPrestamo", ignore = true)
    @Mapping(target = "fechaPrestamo", ignore = true)
    @Mapping(target = "fechaVencimiento", ignore = true)
    @Mapping(target = "estadoPrestamo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    PrestamoEntity toEntity(PrestamoRequest request);

    PrestamoResponse toResponse(PrestamoEntity entity);

    List<PrestamoResponse> toResponseList(List<PrestamoEntity> entities);

}