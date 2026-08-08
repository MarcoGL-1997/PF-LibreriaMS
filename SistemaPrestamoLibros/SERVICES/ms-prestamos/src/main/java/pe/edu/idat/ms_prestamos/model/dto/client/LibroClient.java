package pe.edu.idat.ms_prestamos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.idat.ms_prestamos.model.dto.client.LibroClientResponse;

@FeignClient(name = "ms-libros")
public interface LibroClient {

    @GetMapping("/api/libros/{id}")
    LibroClientResponse obtenerLibroPorId(
            @PathVariable("id") Long id
    );

}