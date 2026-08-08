package pe.edu.idat.ms_notificaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.ms_notificaciones.model.entity.NotificacionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificacionRepository extends JpaRepository<NotificacionEntity, Long> {

    List<NotificacionEntity> findByEstadoTrue();

    Optional<NotificacionEntity> findByIdNotificacionAndEstadoTrue(Long id);

    List<NotificacionEntity> findByIdUsuarioAndEstadoTrue(Long idUsuario);

    List<NotificacionEntity> findByLeidoFalseAndEstadoTrue();

}