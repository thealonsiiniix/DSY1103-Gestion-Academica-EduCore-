package cl.instituto.pacifico.ms_docentes.service;

import cl.instituto.pacifico.ms_docentes.exception.BusinessException;
import cl.instituto.pacifico.ms_docentes.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_docentes.model.Docente;
import cl.instituto.pacifico.ms_docentes.repository.DocenteRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocenteServiceTest {

    @Mock
    private DocenteRepository repository;

    @InjectMocks
    private DocenteService service;

    @Test
    @DisplayName("Listar docentes")
    void listar_retornaLista() {

        List<Docente> docentes = List.of(
                new Docente(
                        1L,
                        "12345678-9",
                        "Juan",
                        "Perez",
                        "juan@gmail.com"
                )
        );

        when(repository.findAll()).thenReturn(docentes);

        List<Docente> resultado = service.listar();

        assertThat(resultado).hasSize(1);
    }

    @Test
    @DisplayName("Buscar docente por ID cuando existe")
    void buscarPorId_cuandoExiste() {

        Docente docente = new Docente(
                1L,
                "12345678-9",
                "Juan",
                "Perez",
                "juan@gmail.com"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(docente));

        Docente resultado = service.buscarPorId(1L);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNombre()).isEqualTo("Juan");
    }

    @Test
    @DisplayName("Buscar docente por ID cuando no existe")
    void buscarPorId_cuandoNoExiste() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.buscarPorId(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Guardar docente correctamente")
    void guardar_docenteNuevo() {

        Docente docente = new Docente(
                1L,
                "12345678-9",
                "Juan",
                "Perez",
                "juan@gmail.com"
        );

        when(repository.findByRut(docente.getRut()))
                .thenReturn(Optional.empty());

        when(repository.findByCorreo(docente.getCorreo()))
                .thenReturn(Optional.empty());

        when(repository.save(docente))
                .thenReturn(docente);

        Docente resultado = service.guardar(docente);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getRut()).isEqualTo("12345678-9");
    }

    @Test
    @DisplayName("Guardar docente con rut repetido")
    void guardar_rutExistente() {

        Docente docente = new Docente(
                1L,
                "12345678-9",
                "Juan",
                "Perez",
                "juan@gmail.com"
        );

        when(repository.findByRut(docente.getRut()))
                .thenReturn(Optional.of(docente));

        assertThatThrownBy(() ->
                service.guardar(docente))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("Guardar docente con correo repetido")
    void guardar_correoExistente() {

        Docente docente = new Docente(
                1L,
                "12345678-9",
                "Juan",
                "Perez",
                "juan@gmail.com"
        );

        when(repository.findByRut(docente.getRut()))
                .thenReturn(Optional.empty());

        when(repository.findByCorreo(docente.getCorreo()))
                .thenReturn(Optional.of(docente));

        assertThatThrownBy(() ->
                service.guardar(docente))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("Actualizar docente")
    void actualizar_docenteExistente() {

        Docente actual = new Docente(
                1L,
                "123",
                "Juan",
                "Perez",
                "juan@gmail.com"
        );

        Docente nuevo = new Docente(
                1L,
                "456",
                "Pedro",
                "Soto",
                "pedro@gmail.com"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(actual));

        when(repository.save(any(Docente.class)))
                .thenReturn(nuevo);

        Docente resultado =
                service.actualizar(1L, nuevo);

        assertThat(resultado.getNombre())
                .isEqualTo("Pedro");
    }

    @Test
    @DisplayName("Actualizar docente cuando no existe")
    void actualizar_cuandoNoExiste() {

        Docente nuevo = new Docente(
                1L,
                "456",
                "Pedro",
                "Soto",
                "pedro@gmail.com"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.actualizar(1L, nuevo))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Eliminar docente")
    void eliminar_docenteExistente() {

        Docente docente = new Docente(
                1L,
                "123",
                "Juan",
                "Perez",
                "juan@gmail.com"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(docente));

        service.eliminar(1L);

        verify(repository).delete(docente);
    }

    @Test
    @DisplayName("Eliminar docente cuando no existe")
    void eliminar_cuandoNoExiste() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.eliminar(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Buscar docente por rut")
    void buscarPorRut() {

        Docente docente = new Docente(
                1L,
                "123",
                "Juan",
                "Perez",
                "juan@gmail.com"
        );

        when(repository.findByRut("123"))
                .thenReturn(Optional.of(docente));

        Docente resultado =
                service.buscarPorRut("123");

        assertThat(resultado.getRut())
                .isEqualTo("123");
    }

    @Test
    @DisplayName("Buscar docente por rut cuando no existe")
    void buscarPorRut_cuandoNoExiste() {

        when(repository.findByRut("999"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.buscarPorRut("999"))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Buscar docente por correo")
    void buscarPorCorreo() {

        Docente docente = new Docente(
                1L,
                "123",
                "Juan",
                "Perez",
                "juan@gmail.com"
        );

        when(repository.findByCorreo("juan@gmail.com"))
                .thenReturn(Optional.of(docente));

        Docente resultado =
                service.buscarPorCorreo("juan@gmail.com");

        assertThat(resultado.getCorreo())
                .isEqualTo("juan@gmail.com");
    }

    @Test
    @DisplayName("Buscar docente por correo cuando no existe")
    void buscarPorCorreo_cuandoNoExiste() {

        when(repository.findByCorreo("noexiste@gmail.com"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.buscarPorCorreo("noexiste@gmail.com"))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Buscar docentes por nombre")
    void buscarPorNombre() {

        List<Docente> lista = List.of(
                new Docente(
                        1L,
                        "123",
                        "Juan",
                        "Perez",
                        "juan@gmail.com"
                )
        );

        when(repository.findByNombre("Juan"))
                .thenReturn(lista);

        List<Docente> resultado =
                service.buscarPorNombre("Juan");

        assertThat(resultado).hasSize(1);
    }

    @Test
    @DisplayName("Buscar docentes por apellido")
    void buscarPorApellido() {

        List<Docente> lista = List.of(
                new Docente(
                        1L,
                        "123",
                        "Juan",
                        "Perez",
                        "juan@gmail.com"
                )
        );

        when(repository.findByApellido("Perez"))
                .thenReturn(lista);

        List<Docente> resultado =
                service.buscarPorApellido("Perez");

        assertThat(resultado).hasSize(1);
    }

    @Test
    @DisplayName("Obtener perfil completo")
    void obtenerPerfilCompleto() {

        Docente docente = new Docente(
                1L,
                "123",
                "Juan",
                "Perez",
                "juan@gmail.com"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(docente));

        Map<String, Object> resultado =
                service.obtenerPerfilCompleto(1L);

        assertThat(resultado).containsKey("docente");
        assertThat(resultado).containsKey("cursos");
        assertThat(resultado).containsKey("evaluaciones");
    }
}