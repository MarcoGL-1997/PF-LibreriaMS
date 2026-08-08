package pe.edu.idat.ms_notificaciones.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.ms_notificaciones.model.dto.NotificacionRequest;
import pe.edu.idat.ms_notificaciones.model.dto.NotificacionResponse;
import pe.edu.idat.ms_notificaciones.service.NotificacionService;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @GetMapping
    public ResponseEntity<List<NotificacionResponse>> listarNotificaciones() {

        return ResponseEntity.ok(
                notificacionService.listarNotificaciones()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponse> obtenerNotificacionPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificacionService.obtenerNotificacionPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<NotificacionResponse> registrarNotificacion(
            @Valid @RequestBody NotificacionRequest request) {

        NotificacionResponse response =
                notificacionService.registrarNotificacion(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<NotificacionResponse>> listarPorUsuario(
            @PathVariable Long idUsuario) {

        return ResponseEntity.ok(
                notificacionService.listarPorUsuario(idUsuario)
        );
    }

    @GetMapping("/noleidas")
    public ResponseEntity<List<NotificacionResponse>> listarNoLeidas() {

        return ResponseEntity.ok(
                notificacionService.listarNoLeidas()
        );
    }

    @PutMapping("/{id}/leer")
    public ResponseEntity<NotificacionResponse> marcarComoLeida(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificacionService.marcarComoLeida(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(
            @PathVariable Long id) {

        notificacionService.eliminarNotificacion(id);

        return ResponseEntity.noContent().build();
    }
}