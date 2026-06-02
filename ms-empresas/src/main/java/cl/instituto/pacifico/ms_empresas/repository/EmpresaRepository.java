package cl.instituto.pacifico.ms_empresas.repository;
import cl.instituto.pacifico.ms_empresas.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
        Optional<Empresa> findByRut(String rut);
        Optional<Empresa> findByEmail(String email);
}