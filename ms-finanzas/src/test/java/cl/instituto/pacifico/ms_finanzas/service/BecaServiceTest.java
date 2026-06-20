package cl.instituto.pacifico.ms_finanzas.service;

import cl.instituto.pacifico.ms_finanzas.exception.BusinessException;
import cl.instituto.pacifico.ms_finanzas.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_finanzas.model.Beca;
import cl.instituto.pacifico.ms_finanzas.repository.BecaRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BecaServiceTest {

    @Mock
    private BecaRepository repository;

    @InjectMocks
    private BecaService service;

    @Test
    @DisplayName("Listar becas")
    void listar_retornaLista() {
        List<Beca> lista = List.of(
                new Beca(1L, "Beca Excelencia", 50.0)
        );

        when(repository.findAll()).thenReturn(lista);

        List<Beca> resultado = service.listar();

        assertThat(resultado).hasSize(1);
    }

    @Test
    @DisplayName("Buscar beca por ID existente")
    void buscarPorId_existente() {
        Beca beca = new Beca(1L, "Beca Excelencia", 50.0);

        when(repository.findById(1L))
                .thenReturn(Optional.of(beca));

        Beca resultado = service.buscarPorId(1L);

        assertThat(resultado.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Buscar beca por ID inexistente")
    void buscarPorId_noExiste() {
        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Guardar beca válida")
    void guardar_correctamente() {
        Beca beca = new Beca(1L, "Beca Excelencia", 50.0);

        when(repository.save(beca))
                .thenReturn(beca);

        Beca resultado = service.guardar(beca);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getPorcentaje()).isEqualTo(50.0);
    }

    @Test
    @DisplayName("Guardar beca con porcentaje menor a 0")
    void guardar_porcentajeMenorACero() {
        Beca beca = new Beca(1L, "Beca Error", -10.0);

        assertThatThrownBy(() -> service.guardar(beca))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("Guardar beca con porcentaje mayor a 100")
    void guardar_porcentajeMayorA100() {
        Beca beca = new Beca(1L, "Beca Error", 120.0);

        assertThatThrownBy(() -> service.guardar(beca))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("Actualizar beca existente")
    void actualizar_existente() {
        Beca actual = new Beca(1L, "Beca Antigua", 30.0);
        Beca nueva = new Beca(1L, "Beca Nueva", 60.0);

        when(repository.findById(1L))
                .thenReturn(Optional.of(actual));

        when(repository.save(any(Beca.class)))
                .thenReturn(nueva);

        Beca resultado = service.actualizar(1L, nueva);

        assertThat(resultado.getNombre()).isEqualTo("Beca Nueva");
        assertThat(resultado.getPorcentaje()).isEqualTo(60.0);
    }

    @Test
    @DisplayName("Actualizar beca inexistente")
    void actualizar_noExiste() {
        Beca nueva = new Beca(1L, "Beca Nueva", 60.0);

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.actualizar(1L, nueva))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Actualizar beca con porcentaje inválido")
    void actualizar_porcentajeInvalido() {
        Beca actual = new Beca(1L, "Beca Antigua", 30.0);
        Beca nueva = new Beca(1L, "Beca Nueva", 150.0);

        when(repository.findById(1L))
                .thenReturn(Optional.of(actual));

        assertThatThrownBy(() -> service.actualizar(1L, nueva))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("Eliminar beca existente")
    void eliminar_existente() {
        Beca beca = new Beca(1L, "Beca Excelencia", 50.0);

        when(repository.findById(1L))
                .thenReturn(Optional.of(beca));

        service.eliminar(1L);

        verify(repository).delete(beca);
    }

    @Test
    @DisplayName("Eliminar beca inexistente")
    void eliminar_noExiste() {
        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.eliminar(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
