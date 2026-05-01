package cl.instituto.pacifico.ms_empresas.repository;
import cl.instituto.pacifico.ms_empresas.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}