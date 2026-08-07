package pe.edu.idat.ms_multas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.idat.ms_multas.model.dto.client.PrestamoClientResponse;

@FeignClient(name = "ms-prestamos")
public interface PrestamoClient {

    @GetMapping("/api/prestamos/{id}")
    PrestamoClientResponse obtenerPrestamoPorId(
            @PathVariable("id") Long id
    );
}