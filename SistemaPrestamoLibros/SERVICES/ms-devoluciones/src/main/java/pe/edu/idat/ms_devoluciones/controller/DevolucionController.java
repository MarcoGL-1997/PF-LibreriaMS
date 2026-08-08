package pe.edu.idat.ms_devoluciones.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.ms_devoluciones.model.dto.DevolucionRequest;
import pe.edu.idat.ms_devoluciones.model.dto.DevolucionResponse;
import pe.edu.idat.ms_devoluciones.service.DevolucionService;

import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")
public class DevolucionController {

    private final DevolucionService devolucionService;

    public DevolucionController(
            DevolucionService devolucionService) {

        this.devolucionService = devolucionService;
    }

    @GetMapping
    public ResponseEntity<List<DevolucionResponse>> listarDevoluciones() {

        return ResponseEntity.ok(
                devolucionService.listarDevoluciones()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevolucionResponse> obtenerDevolucionPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                devolucionService.obtenerDevolucionPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<DevolucionResponse> registrarDevolucion(
            @Valid @RequestBody DevolucionRequest request) {

        DevolucionResponse response =
                devolucionService.registrarDevolucion(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/prestamo/{idPrestamo}")
    public ResponseEntity<DevolucionResponse> obtenerPorPrestamo(
            @PathVariable Long idPrestamo) {

        return ResponseEntity.ok(
                devolucionService.obtenerDevolucionPorPrestamo(idPrestamo)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDevolucion(
            @PathVariable Long id) {

        devolucionService.eliminarDevolucion(id);

        return ResponseEntity.noContent().build();
    }
}