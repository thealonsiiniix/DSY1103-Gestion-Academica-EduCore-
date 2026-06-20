package cl.instituto.pacifico.ms_docentes.service;

import cl.instituto.pacifico.ms_docentes.exception.BusinessException;
import cl.instituto.pacifico.ms_docentes.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_docentes.model.Docente;
import cl.instituto.pacifico.ms_docentes.repository.DocenteRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DocenteService {

    private static final Logger log =
            LoggerFactory.getLogger(DocenteService.class);

    private final DocenteRepository repository;

    public DocenteService(DocenteRepository repository) {
        this.repository = repository;
    }

    public List<Docente> listar() {

        log.info("Listando docentes");

        return repository.findAll();
    }

    public Docente buscarPorId(Long id) {

        log.info("Buscando docente {}", id);

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Docente no encontrado"));
    }

    public Docente guardar(Docente docente) {

        if (repository.findByRut(docente.getRut()).isPresent()) {

            log.error("El rut {} ya existe",
                    docente.getRut());

            throw new BusinessException(
                    "El rut ya existe");
        }

        if (repository.findByCorreo(docente.getCorreo()).isPresent()) {

            log.error("El correo {} ya existe",
                    docente.getCorreo());

            throw new BusinessException(
                    "El correo ya existe");
        }

        log.info("Guardando docente {}",
                docente.getNombre());

        return repository.save(docente);
    }

    public Docente actualizar(Long id,
                              Docente nuevo) {

        Docente docente = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Docente no encontrado"));

        docente.setRut(nuevo.getRut());
        docente.setNombre(nuevo.getNombre());
        docente.setApellido(nuevo.getApellido());
        docente.setCorreo(nuevo.getCorreo());

        log.info("Actualizando docente {}", id);

        return repository.save(docente);
    }

    public void eliminar(Long id) {

        Docente docente = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Docente no encontrado"));

        log.warn("Eliminando docente {}", id);

        repository.delete(docente);
    }

    public Docente buscarPorRut(String rut) {

        log.info("Buscando docente por rut {}", rut);

        return repository.findByRut(rut)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Docente no encontrado"));
    }

    public Docente buscarPorCorreo(String correo) {

        log.info("Buscando docente por correo {}", correo);

        return repository.findByCorreo(correo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Docente no encontrado"));
    }

    public List<Docente> buscarPorNombre(String nombre) {

        log.info("Buscando docentes por nombre {}", nombre);

        return repository.findByNombre(nombre);
    }

    public List<Docente> buscarPorApellido(String apellido) {

        log.info("Buscando docentes por apellido {}", apellido);

        return repository.findByApellido(apellido);
    }

    public Map<String, Object> obtenerPerfilCompleto(Long id) {

        Docente docente = buscarPorId(id);

        log.info("Consultando perfil completo {}", id);

        return Map.of(
                "docente", docente,
                "cursos", List.of(),
                "evaluaciones", List.of()
        );
    }
}