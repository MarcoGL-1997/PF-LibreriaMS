package pe.edu.idat.ms_libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.ms_libros.model.entity.LibroEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {

    Optional<LibroEntity> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    List<LibroEntity> findByEstadoTrue();

    Optional<LibroEntity> findByIdLibroAndEstadoTrue(Long idLibro);
}