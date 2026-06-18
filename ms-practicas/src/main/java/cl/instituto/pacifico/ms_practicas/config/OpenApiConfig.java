package cl.instituto.pacifico.ms_practicas.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI configurarOpenApi() {
        Contact contacto = new Contact()
                .name("Equipo Instituto Pacifico")
                .email("equipo@instituto.cl")
                .url("https://www.duocuc.cl");

        License licencia = new License()
                .name("MIT")
                .url("https://opensource.org/licenses/MIT");

        Info informacionMs = new Info()
                .title("MS PRACTICA")
                .version("1.0")
                .description("""
                        Microservicio encargado de administrar
                        las practicas de estudiantes,
                        """)
                .termsOfService("http://www.duocuc.cl")
                .contact(contacto)
                .license(licencia);

        return new OpenAPI()
                .info(informacionMs)
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Repositorio oficial del proyecto en GitHub")
                                .url("https://github.com/thealonsiiniix/DSY1103-Gestion-Academica-EduCore-")
                );
    }
}
