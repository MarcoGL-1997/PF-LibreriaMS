package pe.edu.idat.ms_usuarios.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.idat.ms_usuarios.exception.DuplicateResourceException;
import pe.edu.idat.ms_usuarios.exception.ResourceNotFoundException;
import pe.edu.idat.ms_usuarios.model.dto.UsuarioRequest;
import pe.edu.idat.ms_usuarios.model.dto.UsuarioResponse;
import pe.edu.idat.ms_usuarios.model.entity.UsuarioEntity;
import pe.edu.idat.ms_usuarios.model.mapper.UsuarioMapper;
import pe.edu.idat.ms_usuarios.repository.UsuarioRepository;
import pe.edu.idat.ms_usuarios.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<UsuarioResponse> listarUsuarios() {

        List<UsuarioEntity> usuarios = usuarioRepository.findByEstadoTrue();

        return usuarioMapper.toResponseList(usuarios);
    }

    @Override
    public UsuarioResponse obtenerUsuarioPorId(Long id) {

        UsuarioEntity usuario = obtenerEntidad(id);

        return usuarioMapper.toResponse(usuario);
    }

    @Override
    public UsuarioResponse registrarUsuario(UsuarioRequest request) {

        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new DuplicateResourceException("El correo ya se encuentra registrado.");
        }

        if (usuarioRepository.existsByNumeroDocumento(request.getNumeroDocumento())) {
            throw new DuplicateResourceException("El número de documento ya se encuentra registrado.");
        }

        UsuarioEntity usuario = usuarioMapper.toEntity(request);

        usuario.setEstado(true);
        usuario.setFechaRegistro(LocalDateTime.now());

        UsuarioEntity usuarioGuardado = usuarioRepository.save(usuario);

        return usuarioMapper.toResponse(usuarioGuardado);
    }

    @Override
    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request) {

        UsuarioEntity usuario = obtenerEntidad(id);

        if (!usuario.getCorreo().equals(request.getCorreo())
                && usuarioRepository.existsByCorreo(request.getCorreo())) {

            throw new DuplicateResourceException("El correo ya se encuentra registrado.");
        }

        if (!usuario.getNumeroDocumento().equals(request.getNumeroDocumento())
                && usuarioRepository.existsByNumeroDocumento(request.getNumeroDocumento())) {

            throw new DuplicateResourceException("El número de documento ya se encuentra registrado.");
        }

        usuario.setNombres(request.getNombres());
        usuario.setApellidos(request.getApellidos());
        usuario.setTipoDocumento(request.getTipoDocumento());
        usuario.setNumeroDocumento(request.getNumeroDocumento());
        usuario.setCorreo(request.getCorreo());
        usuario.setTelefono(request.getTelefono());
        usuario.setDireccion(request.getDireccion());

        UsuarioEntity usuarioActualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toResponse(usuarioActualizado);
    }

    @Override
    public void eliminarUsuario(Long id) {

        UsuarioEntity usuario = obtenerEntidad(id);

        usuario.setEstado(false);

        usuarioRepository.save(usuario);
    }

    private UsuarioEntity obtenerEntidad(Long id) {

      return usuarioRepository.findByIdUsuarioAndEstadoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado con ID: " + id));
    }

}