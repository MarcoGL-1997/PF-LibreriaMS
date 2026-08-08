package pe.edu.idat.ms_usuarios.service;

import pe.edu.idat.ms_usuarios.model.dto.UsuarioRequest;
import pe.edu.idat.ms_usuarios.model.dto.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponse> listarUsuarios();

    UsuarioResponse obtenerUsuarioPorId(Long id);

    UsuarioResponse registrarUsuario(UsuarioRequest request);

    UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request);

    void eliminarUsuario(Long id);

}