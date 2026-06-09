package cl.instituto.pacifico.ms_finanzas.service;

import cl.instituto.pacifico.ms_finanzas.exception.BusinessException;
import cl.instituto.pacifico.ms_finanzas.exception.ResourceNotFoundException;
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
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Beca no encontrada"));
    }

    public Beca guardar(Beca beca) {

        log.info("Guardando beca");

        if (beca.getPorcentaje() < 0 ||
                beca.getPorcentaje() > 100) {

            throw new BusinessException(
                    "El porcentaje debe estar entre 0 y 100");
        }

        return repository.save(beca);
    }

    public Beca actualizar(
            Long id,
            Beca becaActualizada) {

        log.info("Actualizando beca con ID {}", id);

        Beca beca = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Beca no encontrada"));

        if (becaActualizada.getPorcentaje() < 0 ||
                becaActualizada.getPorcentaje() > 100) {

            throw new BusinessException(
                    "El porcentaje debe estar entre 0 y 100");
        }

        beca.setNombre(
                becaActualizada.getNombre());

        beca.setPorcentaje(
                becaActualizada.getPorcentaje());

        return repository.save(beca);
    }

    public void eliminar(Long id) {

        log.info("Eliminando beca con ID {}", id);

        Beca beca = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Beca no encontrada"));

        repository.delete(beca);
    }
}