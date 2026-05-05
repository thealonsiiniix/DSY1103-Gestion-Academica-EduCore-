package cl.instituto.pacifico.ms_matriculas.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityController {
    // Endpoint publico
    @GetMapping("/publico")
    public String publico() {
        return "Endpoint Publico 🔓";
    }

    // Endpoint que requiere autenticacion
    @GetMapping("/privado")
    public String privado() {
        return "Endpoint Privado 🔒";
    }

    // Enpoint solo para ADMIN
    @GetMapping("/admin")
    public String admin() {
        return "Endpoint SOLO ADMIN 🔐";
    }
}
