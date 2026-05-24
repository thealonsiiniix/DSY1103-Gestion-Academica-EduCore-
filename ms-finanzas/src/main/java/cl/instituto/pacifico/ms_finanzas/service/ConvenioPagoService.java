package cl.instituto.pacifico.ms_finanzas.service;

import cl.instituto.pacifico.ms_finanzas.model.ConvenioPago;
import cl.instituto.pacifico.ms_finanzas.repository.ConvenioPagoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConvenioPagoService {

    private static final Logger log = LoggerFactory.getLogger(ConvenioPagoService.class);
    private final ConvenioPagoRepository repository;
    public ConvenioPagoService(ConvenioPagoRepository repository) {
        this.repository = repository;
    }


    public List<ConvenioPago> listar() {
        log.info("Listando convenios de pago");
        return repository.findAll();
    }


    public ConvenioPago buscarPorId(Long id) {
        log.info("Buscando convenio con ID {}", id);
        return repository.findById(id)
                .orElse(null);
    }

    public ConvenioPago guardar(ConvenioPago convenioPago) {
        log.info("Guardando convenio de pago");
        return repository.save(convenioPago);
    }


    public ConvenioPago actualizar(Long id, ConvenioPago convenioActualizado) {
        log.info("Actualizando convenio con ID {}", id);
        ConvenioPago convenio = repository.findById(id)
                .orElse(null);

        if (convenio == null) {
            return null;
        }
        convenio.setCuotas(convenioActualizado.getCuotas());
        convenio.setMontoTotal(convenioActualizado.getMontoTotal());
        return repository.save(convenio);
    }

    public ConvenioPago eliminar(Long id) {
        log.info("Eliminando convenio con ID {}", id);
        ConvenioPago convenio = repository.findById(id)
                .orElse(null);
        if (convenio == null) {
            return null;
        }
        repository.delete(convenio);
        return convenio;
    }
}