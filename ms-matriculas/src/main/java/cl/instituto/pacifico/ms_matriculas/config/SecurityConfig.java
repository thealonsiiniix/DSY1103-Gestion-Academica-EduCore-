package cl.instituto.pacifico.ms_matriculas.config;
import cl.instituto.pacifico.ms_matriculas.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final UsuarioRepository usuarioRepository;
    public SecurityConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Configuracion de seguridad principal
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/publico/**").permitAll()
                        .requestMatchers("/api/matriculas/**").hasAnyRole("ADMIN", "DOCENTE")
                        .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }

    // Bean que defina los usuarios del sistema
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> usuarioRepository.findByUsername(username)
                .map(usuario -> User.withUsername(usuario.getUsername())
                        .password("{noop}" + usuario.getPassword())
                        .roles(usuario.getRole())
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}