package pe.edu.idat.ms_multas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.ms_multas.model.entity.MultaEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MultaRepository extends JpaRepository<MultaEntity, Long> {

    List<MultaEntity> findByEstadoTrue();

    Optional<MultaEntity> findByIdMultaAndEstadoTrue(Long idMulta);

    Optional<MultaEntity> findByIdPrestamoAndEstadoTrue(Long idPrestamo);

    List<MultaEntity> findByEstadoMultaAndEstadoTrue(String estadoMulta);

    boolean existsByIdPrestamoAndEstadoTrue(Long idPrestamo);
}