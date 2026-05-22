package cl.instituto.pacifico.ms_finanzas.repository;

import cl.instituto.pacifico.ms_finanzas.model.Arancel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArancelRepository extends JpaRepository<Arancel, Long> {
    Arancel findByEstudianteRut(String rut); // NO CAMBIAR
}