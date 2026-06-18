package cl.instituto.pacifico.ms_academico.service;

import cl.instituto.pacifico.ms_academico.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_academico.model.Carrera;
import cl.instituto.pacifico.ms_academico.repository.CarreraRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class CarreraServiceTest {

    @Mock
    private CarreraRepository repository;

    // inyectar datos falsos al servicio
    @InjectMocks
    private CarreraService service;

    // Test CREAR
    @Test
    @DisplayName("Crea una carrera")
    void ingresarCarrera_guardaCarrera(){
        // crear instacia de carrera
        Carrera carrera = new Carrera(1L, "Programacion", "Base de datos", "B", "Fundamentos de BSD" );

        when(repository.save(carrera)).thenReturn(carrera);

        Carrera resultado = service.crear(carrera);

        assertNotNull(resultado.getId());
        verify(repository).save(carrera);

    }

    @Test
    @DisplayName("lista todas las carreras")
    void listarCarrera_RetornaLasCarreras() {
        List<Carrera> lista = Arrays.asList(
                new Carrera(1L, "Programacion", "Base de datos", "B", "Fundamentos de BSD"),
                new Carrera(2L, "Administracion", "Finanzas", "B", "Fundamentos de finanzas")
        );

        when(repository.findAll()).thenReturn(lista);

        List<Carrera> resultado = service.listar();

        assertThat(resultado).hasSize(2);
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Busca carreras por nombre")
    void buscarPorCarrera_ListaCarreraPorNombre() {
        List<Carrera> lista = Arrays.asList(
                new Carrera(1L, "Informatica", "Prog", "A", "Base"),
                new Carrera(2L, "Informatica", "Avanzado", "B", "POO")
        );

        when(repository.findByCarrera("Informatica")).thenReturn(lista);

        List<Carrera> resultado = service.buscarPorCarrera("Informatica");

        assertNotNull(resultado);
        assertThat(resultado).hasSize(2);
        verify(repository).findByCarrera("Informatica");
    }


    @Test
    @DisplayName("Retorna lista vacía cuando no existen carreras con ese nombre")
    void buscarPorCarrera_ListaVaciaCarreraInexistente() {
        when(repository.findByCarrera("Medicina")).thenReturn(List.of());

        List<Carrera> resultado = service.buscarPorCarrera("Medicina");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(repository).findByCarrera("Medicina");
    }


    @Test
    @DisplayName("Retorna carrera cuando existe el ID")
    void BuscarPorId_RetornalaCarreraPorId() {
        Carrera carrera = new Carrera(1L, "Programacion", "Base de datos", "B", "Fundamentos de BSD" );

        when(repository.findById(1L)).thenReturn(Optional.of(carrera));

        Carrera resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        assertThat(resultado.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Retornar una expecion")
    void BuscarPorId_RetornaExepcion() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(1L)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Eliminar carrera cuando existe")
    void EliminarPorId_EliminaCarreraPorId() {
        Carrera carrera = new Carrera(1L, "Programacion", "Base de datos", "B", "Fundamentos de BSD" );

        when(repository.findById(1L)).thenReturn(Optional.of(carrera));

        boolean resultado = service.eliminar(1L);

        assertTrue(resultado);
        verify(repository).deleteById(1L);
    }

    // ELIMINAR (no existe)
    @Test
    @DisplayName("Elimina carrera con id inexistente")
    void testEliminarNoExiste() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        boolean resultado = service.eliminar(1L);

        assertFalse(resultado);
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Actualizar carrera cuando existe")
    void ActualizaCarrera_ActualizarExiste() {
        Carrera existente = new Carrera(1L, "Informatica", "Prog", "A", "Base");
        Carrera nueva = new Carrera(null, "Adminstracion", "Contabilidad", "B", "Finanzas");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any(Carrera.class))).thenReturn(existente);

        Carrera resultado = service.actualizar(1L, nueva);

        assertNotNull(resultado);
        assertEquals("Adminstracion", resultado.getCarrera());
        verify(repository).save(existente);
    }

    @Test
    @DisplayName("Actualizar estudiante inexistente")
    void ActualizaCarrera_ActualizarNoExiste() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Carrera resultado = service.actualizar(1L, new Carrera());

        assertNull(resultado);
    }
}