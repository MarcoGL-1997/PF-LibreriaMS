package pe.edu.idat.ms_libros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsLibrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsLibrosApplication.class, args);
	}

}
