package cl.instituto.pacifico.ms_matriculas.service;
import cl.instituto.pacifico.ms_matriculas.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_matriculas.model.Matricula;
import cl.instituto.pacifico.ms_matriculas.repository.MatriculaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatriculaServiceTest {
    @Mock
    private MatriculaRepository repository;

    @InjectMocks
    private MatriculaService service;

    private Matricula crearMatricula() {
        return new Matricula(
                1L,
                1L,
                1L,
                "A-101",
                LocalDate.now(),
                "ACTIVA"
        );
    }

    @Test
    @DisplayName("Debe listar todas las matriculas")
    void listar_debeRetornarLista() {

        List<Matricula> lista = List.of(crearMatricula());

        when(repository.findAll()).thenReturn(lista);

        List<Matricula> resultado = service.listar();

        assertThat(resultado).hasSize(1);

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Debe obtener matricula por id")
    void obtener_existente() {

        Matricula matricula = crearMatricula();

        when(repository.findById(1L))
                .thenReturn(Optional.of(matricula));

        Matricula resultado = service.obtener(1L);

        assertThat(resultado.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepcion cuando no existe")
    void obtener_noExiste() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.obtener(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Debe eliminar matricula correctamente")
    void eliminar_correctamente() {

        Matricula matricula = crearMatricula();

        when(repository.findById(1L))
                .thenReturn(Optional.of(matricula));

        service.eliminar(1L);

        verify(repository).delete(matricula);
    }

    @Test
    @DisplayName("Debe lanzar excepcion al eliminar si no existe")
    void eliminar_noExiste() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.eliminar(99L))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(repository, never()).delete(any());
    }
}