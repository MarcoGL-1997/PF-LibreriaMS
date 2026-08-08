package pe.edu.idat.ms_devoluciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.ms_devoluciones.model.entity.DevolucionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevolucionRepository
        extends JpaRepository<DevolucionEntity, Long> {

    List<DevolucionEntity> findByEstadoTrue();

    Optional<DevolucionEntity> findByIdDevolucionAndEstadoTrue(
            Long idDevolucion
    );

    Optional<DevolucionEntity> findByIdPrestamoAndEstadoTrue(
            Long idPrestamo
    );

}