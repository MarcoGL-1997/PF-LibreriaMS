package pe.edu.idat.ms_usuarios.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pe.edu.idat.ms_usuarios.model.dto.UsuarioRequest;
import pe.edu.idat.ms_usuarios.model.dto.UsuarioResponse;
import pe.edu.idat.ms_usuarios.model.entity.UsuarioEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    UsuarioEntity toEntity(UsuarioRequest request);

    UsuarioResponse toResponse(UsuarioEntity entity);

    List<UsuarioResponse> toResponseList(List<UsuarioEntity> entities);

}