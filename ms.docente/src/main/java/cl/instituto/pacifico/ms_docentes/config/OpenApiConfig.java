package cl.instituto.pacifico.ms_docentes.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI configurarOpenApi() {

        Contact contacto = new Contact()
                .name("Equipo EduCore")
                .email("equipo@instituto.cl")
                .url("https://www.duocuc.cl");

        License licencia = new License()
                .name("MIT")
                .url("https://opensource.org/licenses/MIT");

        Info informacionMs = new Info()
                .title("MS Docentes")
                .version("1.0.0")
                .description("""
                        Microservicio encargado de la gestión
                        de docentes dentro del sistema
                        EduCore.

                        Permite:
                        - Registrar docentes
                        - Buscar docentes
                        - Actualizar docentes
                        - Eliminar docentes
                        - Consultar perfiles completos
                        """)
                .termsOfService("https://www.duocuc.cl")
                .contact(contacto)
                .license(licencia);

        Server localServer = new Server()
                .url("http://localhost:8082")
                .description("Servidor Local de Desarrollo");

        return new OpenAPI()
                .info(informacionMs)
                .servers(List.of(localServer))
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Repositorio oficial del proyecto")
                                .url("https://github.com/thealonsiiniix/DSY1103-Gestion-Academica-EduCore-")
                );
    }
}