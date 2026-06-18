package cl.instituto.pacifico.ms_practicas.service;


import cl.instituto.pacifico.ms_practicas.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_practicas.model.Practica;
import cl.instituto.pacifico.ms_practicas.repository.PracticaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PracticaServiceTest {
    @Mock
    private PracticaRepository repository;

    // inyectar datos falsos al servicio
    @InjectMocks
    private PracticaService service;

    @Test
    @DisplayName("Debe listar todas las practicas")
    void testListar() {
        List<Practica> lista = Arrays.asList(
                new Practica(1L, 1L, "222942608", "Juan", "Juan@a.com", "123456789", 1L, "FixTecnologia", "123456789", "Egaña 404", "123900700", "Fix@e.com", true, "Pagado", null),
                new Practica(2L, 2L, "123942702", "Pedro", "Pedro@b.com", "456456789", 2L, "Microsoft", "123456789", "Egaña 402", "456500300", "Microsoft@e.com", true, "Pagado", null)
        );

        when(repository.findAll()).thenReturn(lista);

        List<Practica> resultado = service.listar();

        assertThat(resultado).hasSize(2);
        verify(repository).findAll();
    }

    @Test
    @DisplayName("busca practica por ID existente")
    void testBuscarPorIdExiste() {
        Practica practica = new Practica(1L, 1L, "222942608", "Juan", "Juan@a.com", "123456789", 1L, "FixTecnologia", "123456789", "Egaña 404", "123900700", "Fix@e.com", true, "Pagado", null);

        when(repository.findById(1L)).thenReturn(Optional.of(practica));

        Practica resultado = service.buscarPorId(1L);
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando no existe")
    void testBuscarPorIdNoExiste() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(1L)).isInstanceOf(ResourceNotFoundException.class);

    }

    @Test
    @DisplayName("Debe obtener practicas por rut")
    void testObtenerPorRut() {
        List<Practica> lista = List.of(new Practica(1L, 1L, "3", "Juan", "juan@a.com", "123", 1L, "Empresa1", "111", "Dir", "123", "e@e.com", true, "Pagado", null));

        when(repository.findByRutEstudiante("111")).thenReturn(lista);

        List<Practica> resultado = service.obtenerPorRut("111");

        assertEquals(1, resultado.size());
        verify(repository).findByRutEstudiante("111");
    }


    @Test
    @DisplayName("Debe lanzar excepción si no hay resultados por rut")
    void testObtenerPorRutVacio() {
        when(repository.findByRutEstudiante("111")).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> {service.obtenerPorRut("111");});
    }

    @Test
    @DisplayName("Debe eliminar practica")
    void testEliminar() {
        when(repository.existsById(1L)).thenReturn(true);

        service.eliminar(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar si no existe")
    void testEliminarNoExiste() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> { service.eliminar(1L);});
    }
}
