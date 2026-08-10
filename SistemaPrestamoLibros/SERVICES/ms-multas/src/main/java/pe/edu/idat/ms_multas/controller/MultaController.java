package pe.edu.idat.ms_multas.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.ms_multas.model.dto.MultaRequest;
import pe.edu.idat.ms_multas.model.dto.MultaResponse;
import pe.edu.idat.ms_multas.service.MultaService;

import java.util.List;

@RestController
@RequestMapping("/api/multas")
public class MultaController {

    private final MultaService multaService;

    public MultaController(MultaService multaService) {
        this.multaService = multaService;
    }

    @GetMapping
    public ResponseEntity<List<MultaResponse>> listarMultas() {

        return ResponseEntity.ok(
                multaService.listarMultas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultaResponse> obtenerMultaPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                multaService.obtenerMultaPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<MultaResponse> registrarMulta(
            @Valid @RequestBody MultaRequest request) {

        MultaResponse response =
                multaService.registrarMulta(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/prestamo/{idPrestamo}")
    public ResponseEntity<MultaResponse> obtenerMultaPorPrestamo(
            @PathVariable Long idPrestamo) {

        return ResponseEntity.ok(
                multaService.obtenerMultaPorPrestamo(idPrestamo)
        );
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<MultaResponse>> listarMultasPendientes() {

        return ResponseEntity.ok(
                multaService.listarMultasPendientes()
        );
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<MultaResponse> pagarMulta(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                multaService.pagarMulta(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMulta(
            @PathVariable Long id) {

        multaService.eliminarMulta(id);

        return ResponseEntity.noContent().build();
    }
}       