package pe.edu.idat.ms_libros.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.ms_libros.model.dto.LibroRequest;
import pe.edu.idat.ms_libros.model.dto.LibroResponse;
import pe.edu.idat.ms_libros.service.LibroService;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<LibroResponse>> listarLibros() {

        List<LibroResponse> libros = libroService.listarLibros();

        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroResponse> obtenerLibroPorId(
            @PathVariable Long id) {

        LibroResponse libro = libroService.obtenerLibroPorId(id);

        return ResponseEntity.ok(libro);
    }

    @PostMapping
    public ResponseEntity<LibroResponse> registrarLibro(
            @Valid @RequestBody LibroRequest request) {

        LibroResponse libro = libroService.registrarLibro(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(libro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroResponse> actualizarLibro(
            @PathVariable Long id,
            @Valid @RequestBody LibroRequest request) {

        LibroResponse libro = libroService.actualizarLibro(id, request);

        return ResponseEntity.ok(libro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(
            @PathVariable Long id) {

        libroService.eliminarLibro(id);

        return ResponseEntity.noContent().build();
    }

}