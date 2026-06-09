package cl.instituto.pacifico.ms_finanzas.service;

import cl.instituto.pacifico.ms_finanzas.exception.BusinessException;
import cl.instituto.pacifico.ms_finanzas.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_finanzas.model.ConvenioPago;
import cl.instituto.pacifico.ms_finanzas.repository.ConvenioPagoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenioPagoService {

    private static final Logger log =
            LoggerFactory.getLogger(ConvenioPagoService.class);

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
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Convenio no encontrado"));
    }

    public ConvenioPago guardar(ConvenioPago convenioPago) {

        log.info("Guardando convenio de pago");

        if (convenioPago.getCuotas() <= 0) {
            throw new BusinessException(
                    "Las cuotas deben ser mayores a cero");
        }

        if (convenioPago.getMontoTotal() <= 0) {
            throw new BusinessException(
                    "El monto total debe ser mayor a cero");
        }

        return repository.save(convenioPago);
    }

    public ConvenioPago actualizar(
            Long id,
            ConvenioPago convenioActualizado) {

        log.info("Actualizando convenio con ID {}", id);

        ConvenioPago convenio = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Convenio no encontrado"));

        if (convenioActualizado.getCuotas() <= 0) {
            throw new BusinessException(
                    "Las cuotas deben ser mayores a cero");
        }

        if (convenioActualizado.getMontoTotal() <= 0) {
            throw new BusinessException(
                    "El monto total debe ser mayor a cero");
        }

        convenio.setCuotas(
                convenioActualizado.getCuotas());

        convenio.setMontoTotal(
                convenioActualizado.getMontoTotal());

        return repository.save(convenio);
    }

    public void eliminar(Long id) {

        log.info("Eliminando convenio con ID {}", id);

        ConvenioPago convenio = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Convenio no encontrado"));

        repository.delete(convenio);
    }
}