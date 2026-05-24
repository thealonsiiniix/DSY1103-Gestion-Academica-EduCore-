package cl.instituto.pacifico.ms_finanzas.service;

import cl.instituto.pacifico.ms_finanzas.model.Beca;
import cl.instituto.pacifico.ms_finanzas.repository.BecaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BecaService {

    private static final Logger log =
            LoggerFactory.getLogger(BecaService.class);

    private final BecaRepository repository;

    public BecaService(BecaRepository repository) {
        this.repository = repository;
    }

    public List<Beca> listar() {
        log.info("Listando becas");
        return repository.findAll();
    }


    public Beca buscarPorId(Long id) {
        log.info("Buscando beca con ID {}", id);
        return repository.findById(id)
                .orElse(null);
    }


    public Beca guardar(Beca beca) {
        log.info("Guardando beca");
        return repository.save(beca);
    }


    public Beca actualizar(Long id, Beca becaActualizada) {
        log.info("Actualizando beca con ID {}", id);
        Beca beca = repository.findById(id)
                .orElse(null);

        if (beca == null) {
            return null;
        }
        beca.setNombre(becaActualizada.getNombre());
        beca.setPorcentaje(becaActualizada.getPorcentaje());
        return repository.save(beca);
    }

    public Beca eliminar(Long id) {
        log.info("Eliminando beca con ID {}", id);
        Beca beca = repository.findById(id)
                .orElse(null);
        if (beca == null) {
            return null;
        }
        repository.delete(beca);
        return beca;
    }
}