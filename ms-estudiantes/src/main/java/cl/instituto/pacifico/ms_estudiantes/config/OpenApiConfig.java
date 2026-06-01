package cl.instituto.pacifico.ms_estudiantes.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI configurarOpenApi() {

        Contact contacto = new Contact()
                .name("Equipo EduCore")
                .email("contacto@educore.cl");

        License licencia = new License()
                .name("http://www.duocuc.cl");

        Info informacionMs = new Info()
                .title("MS Estudiantes")
                .version("1.0")
                .description("Microservicio encargado de la gestión de estudiantes")
                .contact(contacto)
                .license(licencia);

        ExternalDocumentation github =
                new ExternalDocumentation()
                        .description("Repositorio oficial del proyecto en GitHub")
                        .url("https://github.com/thealonsiiniix/DSY1103-Gestion-Academica-EduCore-");

        return new OpenAPI()
                .info(informacionMs)
                .externalDocs(github);
    }
}