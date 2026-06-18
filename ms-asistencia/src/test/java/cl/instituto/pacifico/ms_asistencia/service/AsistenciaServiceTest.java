package cl.instituto.pacifico.ms_asistencia.service;


import cl.instituto.pacifico.ms_asistencia.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_asistencia.model.Asistencia;
import cl.instituto.pacifico.ms_asistencia.repository.AsistenciaRepository;
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
public class AsistenciaServiceTest {

    @Mock
    private AsistenciaRepository repository;

    // inyectar datos falsos al servicio
    @InjectMocks
    private AsistenciaService service;


    @Test
    @DisplayName("Debe listar todas las asistencias")
    void testListar() {
        List<Asistencia> lista = Arrays.asList(
                new Asistencia(1L, 1L, "222942508", "Juan", LocalDate.now()),
                new Asistencia(2L, 2L, "222343452", "Pedro", LocalDate.now())
        );

        when(repository.findAll()).thenReturn(lista);

        List<Asistencia> resultado = service.listar();

        assertThat(resultado).hasSize(2);
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Debe retornar asistencia cuando existe")
    void testBuscarPorIdExiste() {
        Asistencia asistencia = new Asistencia(1L, 1L, "111", "Juan", LocalDate.now());

        when(repository.findById(1L)).thenReturn(Optional.of(asistencia));

        Asistencia resultado = service.buscarPorId(1L);
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
    @DisplayName("Debe eliminar cuando existe")
    void testEliminarExiste() {
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

    @Test
    @DisplayName("Debe retornar lista por rut")
    void testObtenerPorRut() {
        List<Asistencia> lista = List.of(new Asistencia(1L, 1L, "111", "Juan", LocalDate.now()));

        when(repository.findByRutEstudiante("3")).thenReturn(lista);

        List<Asistencia> resultado = service.obtenerPorRut("3");

        assertEquals(1, resultado.size());
        verify(repository).findByRutEstudiante("3");
    }

    @Test
    @DisplayName("Debe lanzar excepción si no hay resultados por rut")
    void testObtenerPorRutVacio() {
        when(repository.findByRutEstudiante("999")).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> {service.obtenerPorRut("999");});
    }
}
