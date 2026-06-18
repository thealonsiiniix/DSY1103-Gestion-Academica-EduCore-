package cl.instituto.pacifico.ms_asistencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// activando feing/foraneo para consumir el otro MS-estudiante
@SpringBootApplication
public class MsAsistenciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAsistenciaApplication.class, args);
	}

}
