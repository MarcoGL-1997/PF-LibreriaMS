package pe.edu.idat.ms_libros.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.idat.ms_libros.model.dto.LibroRequest;
import pe.edu.idat.ms_libros.model.dto.LibroResponse;
import pe.edu.idat.ms_libros.model.entity.LibroEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibroMapper {

    @Mapping(target = "idLibro", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "cantidadDisponible", ignore = true)
    LibroEntity toEntity(LibroRequest request);

    LibroResponse toResponse(LibroEntity entity);

    List<LibroResponse> toResponseList(List<LibroEntity> entities);

}