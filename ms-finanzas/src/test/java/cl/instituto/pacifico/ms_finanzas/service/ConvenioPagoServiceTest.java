package cl.instituto.pacifico.ms_finanzas.service;

import cl.instituto.pacifico.ms_finanzas.exception.BusinessException;
import cl.instituto.pacifico.ms_finanzas.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_finanzas.model.ConvenioPago;
import cl.instituto.pacifico.ms_finanzas.repository.ConvenioPagoRepository;

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
class ConvenioPagoServiceTest {

    @Mock
    private ConvenioPagoRepository repository;

    @InjectMocks
    private ConvenioPagoService service;

    @Test
    @DisplayName("Listar convenios")
    void listar_retornaLista() {

        List<ConvenioPago> lista = List.of(
                new ConvenioPago(1L, 12, 1200000.0)
        );

        when(repository.findAll()).thenReturn(lista);

        List<ConvenioPago> resultado = service.listar();

        assertThat(resultado).hasSize(1);
    }

    @Test
    @DisplayName("Buscar convenio por ID existente")
    void buscarPorId_existente() {

        ConvenioPago convenio =
                new ConvenioPago(1L, 12, 1200000.0);

        when(repository.findById(1L))
                .thenReturn(Optional.of(convenio));

        ConvenioPago resultado =
                service.buscarPorId(1L);

        assertThat(resultado.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Buscar convenio por ID inexistente")
    void buscarPorId_noExiste() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.buscarPorId(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Guardar convenio válido")
    void guardar_correctamente() {

        ConvenioPago convenio =
                new ConvenioPago(1L, 12, 1200000.0);

        when(repository.save(convenio))
                .thenReturn(convenio);

        ConvenioPago resultado =
                service.guardar(convenio);

        assertThat(resultado).isNotNull();
    }

    @Test
    @DisplayName("Guardar convenio con cuotas inválidas")
    void guardar_cuotasInvalidas() {

        ConvenioPago convenio =
                new ConvenioPago(1L, 0, 1200000.0);

        assertThatThrownBy(() ->
                service.guardar(convenio))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("Guardar convenio con monto inválido")
    void guardar_montoInvalido() {

        ConvenioPago convenio =
                new ConvenioPago(1L, 12, 0.0);

        assertThatThrownBy(() ->
                service.guardar(convenio))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("Actualizar convenio existente")
    void actualizar_existente() {

        ConvenioPago actual =
                new ConvenioPago(1L, 12, 1200000.0);

        ConvenioPago nuevo =
                new ConvenioPago(1L, 24, 2400000.0);

        when(repository.findById(1L))
                .thenReturn(Optional.of(actual));

        when(repository.save(any(ConvenioPago.class)))
                .thenReturn(nuevo);

        ConvenioPago resultado =
                service.actualizar(1L, nuevo);

        assertThat(resultado.getCuotas()).isEqualTo(24);
    }

    @Test
    @DisplayName("Actualizar convenio inexistente")
    void actualizar_noExiste() {

        ConvenioPago nuevo =
                new ConvenioPago(1L, 24, 2400000.0);

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.actualizar(1L, nuevo))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Actualizar convenio con cuotas inválidas")
    void actualizar_cuotasInvalidas() {

        ConvenioPago actual =
                new ConvenioPago(1L, 12, 1200000.0);

        ConvenioPago nuevo =
                new ConvenioPago(1L, 0, 2400000.0);

        when(repository.findById(1L))
                .thenReturn(Optional.of(actual));

        assertThatThrownBy(() ->
                service.actualizar(1L, nuevo))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("Actualizar convenio con monto inválido")
    void actualizar_montoInvalido() {

        ConvenioPago actual =
                new ConvenioPago(1L, 12, 1200000.0);

        ConvenioPago nuevo =
                new ConvenioPago(1L, 24, 0.0);

        when(repository.findById(1L))
                .thenReturn(Optional.of(actual));

        assertThatThrownBy(() ->
                service.actualizar(1L, nuevo))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("Eliminar convenio existente")
    void eliminar_existente() {

        ConvenioPago convenio =
                new ConvenioPago(1L, 12, 1200000.0);

        when(repository.findById(1L))
                .thenReturn(Optional.of(convenio));

        service.eliminar(1L);

        verify(repository).delete(convenio);
    }

    @Test
    @DisplayName("Eliminar convenio inexistente")
    void eliminar_noExiste() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.eliminar(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
