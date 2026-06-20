package cl.instituto.pacifico.ms_finanzas.service;

import cl.instituto.pacifico.ms_finanzas.exception.BusinessException;
import cl.instituto.pacifico.ms_finanzas.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_finanzas.model.Arancel;
import cl.instituto.pacifico.ms_finanzas.repository.ArancelRepository;

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
class ArancelServiceTest {

    @Mock
    private ArancelRepository repository;

    @InjectMocks
    private ArancelService service;

    @Test
    @DisplayName("Listar aranceles")
    void listar_retornaLista() {

        List<Arancel> lista = List.of(
                new Arancel(
                        1L,
                        "12345678-9",
                        "Juan Perez",
                        1500000.0,
                        "2026-06-20",
                        "PENDIENTE"
                )
        );

        when(repository.findAll()).thenReturn(lista);

        List<Arancel> resultado = service.listar();

        assertThat(resultado).hasSize(1);
    }

    @Test
    @DisplayName("Buscar arancel por ID existente")
    void buscarPorId_existente() {

        Arancel arancel = new Arancel(
                1L,
                "12345678-9",
                "Juan Perez",
                1500000.0,
                "2026-06-20",
                "PENDIENTE"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(arancel));

        Arancel resultado =
                service.buscarPorId(1L);

        assertThat(resultado.getId())
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("Buscar arancel por ID inexistente")
    void buscarPorId_noExiste() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.buscarPorId(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Buscar arancel por rut")
    void buscarPorRut_existente() {

        Arancel arancel = new Arancel(
                1L,
                "12345678-9",
                "Juan Perez",
                1500000.0,
                "2026-06-20",
                "PENDIENTE"
        );

        when(repository.findByEstudianteRut("12345678-9"))
                .thenReturn(arancel);

        Arancel resultado =
                service.buscarPorRut("12345678-9");

        assertThat(resultado.getEstudianteRut())
                .isEqualTo("12345678-9");
    }

    @Test
    @DisplayName("Buscar arancel por rut inexistente")
    void buscarPorRut_noExiste() {

        when(repository.findByEstudianteRut("11111111-1"))
                .thenReturn(null);

        assertThatThrownBy(() ->
                service.buscarPorRut("11111111-1"))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Guardar arancel válido")
    void guardar_correctamente() {

        Arancel arancel = new Arancel(
                1L,
                "12345678-9",
                "Juan Perez",
                1500000.0,
                "2026-06-20",
                "PENDIENTE"
        );

        when(repository.save(arancel))
                .thenReturn(arancel);

        Arancel resultado =
                service.guardar(arancel);

        assertThat(resultado).isNotNull();
    }

    @Test
    @DisplayName("Guardar arancel con monto inválido")
    void guardar_montoInvalido() {

        Arancel arancel = new Arancel(
                1L,
                "12345678-9",
                "Juan Perez",
                -1000.0,
                "2026-06-20",
                "PENDIENTE"
        );

        assertThatThrownBy(() ->
                service.guardar(arancel))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("Actualizar arancel existente")
    void actualizar_existente() {

        Arancel actual = new Arancel(
                1L,
                "12345678-9",
                "Juan Perez",
                1500000.0,
                "2026-06-20",
                "PENDIENTE"
        );

        Arancel nuevo = new Arancel(
                1L,
                "12345678-9",
                "Juan Perez",
                2000000.0,
                "2026-06-20",
                "PAGADO"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(actual));

        when(repository.save(any(Arancel.class)))
                .thenReturn(nuevo);

        Arancel resultado =
                service.actualizar(1L, nuevo);

        assertThat(resultado.getMonto())
                .isEqualTo(2000000.0);
    }

    @Test
    @DisplayName("Eliminar arancel")
    void eliminar_existente() {

        Arancel arancel = new Arancel(
                1L,
                "12345678-9",
                "Juan Perez",
                1500000.0,
                "2026-06-20",
                "PENDIENTE"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(arancel));

        service.eliminar(1L);

        verify(repository).delete(arancel);
    }
}

