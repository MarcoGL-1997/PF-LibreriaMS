package pe.edu.idat.ms_prestamos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.idat.ms_prestamos.model.dto.client.UsuarioClientResponse;

@FeignClient(name = "ms-usuarios")
public interface UsuarioClient {

    @GetMapping("/api/usuarios/{id}")
    UsuarioClientResponse obtenerUsuarioPorId(
            @PathVariable("id") Long id
    );

}