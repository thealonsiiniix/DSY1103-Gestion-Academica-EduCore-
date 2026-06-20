package cl.instituto.pacifico.ms_evaluacion.service;

import cl.instituto.pacifico.ms_evaluacion.dto.ResultadoDTO;
import cl.instituto.pacifico.ms_evaluacion.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_evaluacion.model.Evaluacion;
import cl.instituto.pacifico.ms_evaluacion.repository.EvaluacionRepository;

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
class EvaluacionServiceTest {

    @Mock
    private EvaluacionRepository repository;

    @InjectMocks
    private EvaluacionService service;

    @Test
    @DisplayName("Listar evaluaciones")
    void listar_retornaLista() {

        List<Evaluacion> lista = List.of(
                new Evaluacion(1L, 10L, 6.0)
        );

        when(repository.findAll()).thenReturn(lista);

        List<Evaluacion> resultado = service.listar();

        assertThat(resultado).hasSize(1);
    }

    @Test
    @DisplayName("Buscar evaluación por ID")
    void buscarPorId_existente() {

        Evaluacion evaluacion =
                new Evaluacion(1L, 10L, 6.0);

        when(repository.findById(1L))
                .thenReturn(Optional.of(evaluacion));

        Evaluacion resultado =
                service.buscarPorId(1L);

        assertThat(resultado.getId())
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("Buscar evaluación inexistente")
    void buscarPorId_noExiste() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.buscarPorId(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Eliminar evaluación")
    void eliminar_evaluacionExistente() {

        Evaluacion evaluacion =
                new Evaluacion(1L, 10L, 6.0);

        when(repository.findById(1L))
                .thenReturn(Optional.of(evaluacion));

        service.eliminar(1L);

        verify(repository).delete(evaluacion);
    }

    @Test
    @DisplayName("Resultado APROBADO")
    void calcularResultado_aprobado() {

        List<Evaluacion> notas = List.of(
                new Evaluacion(1L, 10L, 6.0),
                new Evaluacion(2L, 10L, 5.0)
        );

        when(repository.findByMatriculaId(10L))
                .thenReturn(notas);

        ResultadoDTO resultado =
                service.calcularResultado(10L);

        assertThat(resultado.getEstado())
                .isEqualTo("APROBADO");
    }

    @Test
    @DisplayName("Resultado EXAMEN")
    void calcularResultado_examen() {

        List<Evaluacion> notas = List.of(
                new Evaluacion(1L, 10L, 3.5),
                new Evaluacion(2L, 10L, 3.5)
        );

        when(repository.findByMatriculaId(10L))
                .thenReturn(notas);

        ResultadoDTO resultado =
                service.calcularResultado(10L);

        assertThat(resultado.getEstado())
                .isEqualTo("EXAMEN");
    }

    @Test
    @DisplayName("Resultado REPROBADO")
    void calcularResultado_reprobado() {

        List<Evaluacion> notas = List.of(
                new Evaluacion(1L, 10L, 2.0),
                new Evaluacion(2L, 10L, 3.0)
        );

        when(repository.findByMatriculaId(10L))
                .thenReturn(notas);

        ResultadoDTO resultado =
                service.calcularResultado(10L);

        assertThat(resultado.getEstado())
                .isEqualTo("REPROBADO");
    }

    @Test
    @DisplayName("Sin evaluaciones")
    void calcularResultado_sinEvaluaciones() {

        when(repository.findByMatriculaId(10L))
                .thenReturn(List.of());

        assertThatThrownBy(() ->
                service.calcularResultado(10L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
