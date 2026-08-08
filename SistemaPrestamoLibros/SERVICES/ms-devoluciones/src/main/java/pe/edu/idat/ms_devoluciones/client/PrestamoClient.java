package pe.edu.idat.ms_devoluciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import pe.edu.idat.ms_devoluciones.model.dto.client.PrestamoClientResponse;

@FeignClient(name = "ms-prestamos")
public interface PrestamoClient {

    @GetMapping("/api/prestamos/{id}")
    PrestamoClientResponse obtenerPrestamoPorId(
            @PathVariable("id") Long id
    );

    @PutMapping("/api/prestamos/{id}/finalizar")
    PrestamoClientResponse finalizarPrestamo(
            @PathVariable("id") Long id
    );

}