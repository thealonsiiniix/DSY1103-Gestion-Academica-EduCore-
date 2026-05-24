package cl.instituto.pacifico.ms_finanzas.service;

import cl.instituto.pacifico.ms_finanzas.model.Arancel;
import cl.instituto.pacifico.ms_finanzas.repository.ArancelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArancelService {

    private static final Logger log =
            LoggerFactory.getLogger(ArancelService.class);

    private final ArancelRepository repository;

    public ArancelService(ArancelRepository repository) {
        this.repository = repository;
    }


    public List<Arancel> listar() {
        log.info("Obteniendo lista de aranceles");
        return repository.findAll();
    }


    public Arancel buscarPorId(Long id) {
        log.info("Buscando arancel con ID {}", id);
        return repository.findById(id)
                .orElse(null);
    }


    public Arancel buscarPorRut(String rut) {
        log.info("Buscando arancel por rut {}", rut);
        return repository.findByEstudianteRut(rut);
    }


    public Arancel guardar(Arancel arancel) {
        log.info("Guardando arancel");
        return repository.save(arancel);
    }


    public Arancel actualizar(Long id, Arancel arancelActualizado) {
        log.info("Actualizando arancel con ID {}", id);
        Arancel arancel = repository.findById(id)
                .orElse(null);
        if (arancel == null) {
            return null;
        }
        arancel.setEstudianteRut(arancelActualizado.getEstudianteRut());
        arancel.setNombre(arancelActualizado.getNombre());
        arancel.setMonto(arancelActualizado.getMonto());
        arancel.setFecha(arancelActualizado.getFecha());
        arancel.setEstado(arancelActualizado.getEstado());
        return repository.save(arancel);
    }


    public Arancel eliminar(Long id) {
        log.info("Eliminando arancel con ID {}", id);
        Arancel arancel = repository.findById(id)
                .orElse(null);
        if (arancel == null) {
            return null;
        }
        repository.delete(arancel);
        return arancel;
    }
}