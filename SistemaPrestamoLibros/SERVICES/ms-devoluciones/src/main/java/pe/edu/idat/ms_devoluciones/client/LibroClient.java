package pe.edu.idat.ms_devoluciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "ms-libros")
public interface LibroClient {

    @PutMapping("/api/libros/{id}/aumentar-disponibilidad")
    void aumentarDisponibilidad(
            @PathVariable("id") Long id
    );
}