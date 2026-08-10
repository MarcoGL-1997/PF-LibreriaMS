package pe.edu.idat.ms_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.ms_usuarios.model.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    boolean existsByNumeroDocumento(String numeroDocumento);

    List<UsuarioEntity> findByEstadoTrue();

    Optional<UsuarioEntity> findByIdUsuarioAndEstadoTrue(Long idUsuario);
}