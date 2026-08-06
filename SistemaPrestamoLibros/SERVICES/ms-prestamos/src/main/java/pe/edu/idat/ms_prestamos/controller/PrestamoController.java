package pe.edu.idat.ms_prestamos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.ms_prestamos.model.dto.PrestamoRequest;
import pe.edu.idat.ms_prestamos.model.dto.PrestamoResponse;
import pe.edu.idat.ms_prestamos.service.PrestamoService;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public ResponseEntity<List<PrestamoResponse>> listarPrestamos() {

        return ResponseEntity.ok(
                prestamoService.listarPrestamos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> obtenerPrestamoPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                prestamoService.obtenerPrestamoPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<PrestamoResponse> registrarPrestamo(
            @Valid @RequestBody PrestamoRequest request) {

        PrestamoResponse response =
                prestamoService.registrarPrestamo(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PrestamoResponse>> listarPrestamosPorUsuario(
            @PathVariable Long idUsuario) {

        return ResponseEntity.ok(
                prestamoService.listarPrestamosPorUsuario(idUsuario)
        );
    }

    @GetMapping("/activos")
    public ResponseEntity<List<PrestamoResponse>> listarPrestamosActivos() {

        return ResponseEntity.ok(
                prestamoService.listarPrestamosActivos()
        );
    }

    @GetMapping("/vencidos")
    public ResponseEntity<List<PrestamoResponse>> listarPrestamosVencidos() {

        return ResponseEntity.ok(
                prestamoService.listarPrestamosVencidos()
        );
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<PrestamoResponse> finalizarPrestamo(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                prestamoService.finalizarPrestamo(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(
            @PathVariable Long id) {

        prestamoService.eliminarPrestamo(id);

        return ResponseEntity.noContent().build();
    }

}