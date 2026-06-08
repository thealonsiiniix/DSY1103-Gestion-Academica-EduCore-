package cl.instituto.pacifico.ms_titulacion.service;
import cl.instituto.pacifico.ms_titulacion.model.Titulacion;
import cl.instituto.pacifico.ms_titulacion.repository.TitulacionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TitulacionServiceTest {
    @Mock
    private TitulacionRepository repository;
    @InjectMocks
    private TitulacionService service;
    @Test
    @DisplayName("Debe listar todas las titulaciones")
    void listar_retornaLista() {
        List<Titulacion> lista = List.of(
                new Titulacion(1L, 10L,
                        LocalDate.now(), "TITULADO")
        );

        when(repository.findAll()).thenReturn(lista);

        List<Titulacion> resultado = service.listar();

        assertThat(resultado).hasSize(1);
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Debe obtener una titulacion por ID")
    void obtener_existente() {

        Titulacion titulacion = new Titulacion(
                1L,
                10L,
                LocalDate.now(),
                "TITULADO"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(titulacion));

        Titulacion resultado = service.obtener(1L);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Retorna null cuando la titulacion no existe")
    void obtener_noExiste() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        Titulacion resultado = service.obtener(99L);

        assertThat(resultado).isNull();
    }

    @Test
    @DisplayName("Actualiza correctamente una titulacion")
    void actualizar_existente() {

        Titulacion actual = new Titulacion(
                1L,
                10L,
                LocalDate.of(2026,6,1),
                "PENDIENTE"
        );

        Titulacion nueva = new Titulacion(
                null,
                10L,
                LocalDate.of(2026,6,10),
                "TITULADO"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(actual));

        when(repository.save(any(Titulacion.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        Titulacion resultado =
                service.actualizar(1L, nueva);

        assertThat(resultado.getEstado())
                .isEqualTo("TITULADO");

        assertThat(resultado.getFecha())
                .isEqualTo(LocalDate.of(2026,6,10));

        verify(repository).save(actual);
    }

    @Test
    @DisplayName("No actualiza si la titulacion no existe")
    void actualizar_noExiste() {

        Titulacion nueva = new Titulacion();

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        Titulacion resultado =
                service.actualizar(1L, nueva);

        assertThat(resultado).isNull();

        verify(repository, never())
                .save(any());
    }

    @Test
    @DisplayName("Debe eliminar una titulacion")
    void eliminar_correctamente() {

        service.eliminar(1L);

        verify(repository).deleteById(1L);
    }
}