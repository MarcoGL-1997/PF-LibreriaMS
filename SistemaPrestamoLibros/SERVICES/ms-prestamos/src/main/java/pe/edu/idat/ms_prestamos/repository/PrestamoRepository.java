package pe.edu.idat.ms_prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.ms_prestamos.model.entity.PrestamoEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Long> {

    List<PrestamoEntity> findByEstadoTrue();

    Optional<PrestamoEntity> findByIdPrestamoAndEstadoTrue(Long idPrestamo);

    List<PrestamoEntity> findByIdUsuarioAndEstadoTrue(Long idUsuario);

    List<PrestamoEntity> findByEstadoPrestamoAndEstadoTrue(String estadoPrestamo);

    List<PrestamoEntity> findByFechaVencimientoBeforeAndEstadoPrestamoAndEstadoTrue(
            LocalDate fecha,
            String estadoPrestamo
    );

    boolean existsByIdUsuarioAndIdLibroAndEstadoPrestamoAndEstadoTrue(
            Long idUsuario,
            Long idLibro,
            String estadoPrestamo
    );

}