package cl.instituto.pacifico.ms_estudiantes.service;
import cl.instituto.pacifico.ms_estudiantes.exception.BusinessException;
import cl.instituto.pacifico.ms_estudiantes.model.Estudiante;
import cl.instituto.pacifico.ms_estudiantes.repository.EstudianteRepository;
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
class EstudianteServiceTest {
    @Mock
    private EstudianteRepository repository;
    @InjectMocks
    private EstudianteService service;
    private Estudiante crearEstudiante() {
        return new Estudiante(1L, "20.123.456-7", "Juan Perez", "juan@gmail.com", "912345678", LocalDate.of(2000, 5, 10), LocalDate.now());}

    @Test
    @DisplayName("Listar estudiantes")
    void listar_retornaLista() {
        List<Estudiante> estudiantes = List.of(crearEstudiante(), crearEstudiante());
        when(repository.findAll()).thenReturn(estudiantes);
        List<Estudiante> resultado = service.listar();
        assertThat(resultado).hasSize(2);
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Buscar estudiante por ID existente")
    void buscarPorId_existente() {
        Estudiante estudiante = crearEstudiante();
        when(repository.findById(1L)).thenReturn(Optional.of(estudiante));
        Estudiante resultado = service.buscarPorId(1L);
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Buscar estudiante por ID inexistente")
    void buscarPorId_inexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        Estudiante resultado = service.buscarPorId(99L);
        assertThat(resultado).isNull();
    }

    @Test
    @DisplayName("Buscar estudiante por RUT")
    void buscarPorRut() {
        Estudiante estudiante = crearEstudiante();
        when(repository.findByRut("20.123.456-7")).thenReturn(estudiante);
        Estudiante resultado = service.buscarPorRut("20.123.456-7");
        assertThat(resultado).isNotNull();
        assertThat(resultado.getRut()).isEqualTo("20.123.456-7");
    }

    @Test
    @DisplayName("Guardar estudiante correctamente")
    void guardar_correctamente() {
        Estudiante estudiante = crearEstudiante();
        when(repository.findByRut(estudiante.getRut())).thenReturn(null);
        when(repository.findByEmail(estudiante.getEmail())).thenReturn(Optional.empty());
        when(repository.save(estudiante)).thenReturn(estudiante);
        Estudiante resultado = service.guardar(estudiante);
        assertThat(resultado).isNotNull();
        verify(repository).save(estudiante);
    }

    @Test
    @DisplayName("Guardar estudiante con rut duplicado")
    void guardar_rutDuplicado() {
        Estudiante estudiante = crearEstudiante();
        when(repository.findByRut(estudiante.getRut())).thenReturn(estudiante);
        assertThatThrownBy(() -> service.guardar(estudiante))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Ya existe un estudiante con el rut");
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Guardar estudiante con email duplicado")
    void guardar_emailDuplicado() {
        Estudiante estudiante = crearEstudiante();
        when(repository.findByRut(estudiante.getRut())).thenReturn(null);
        when(repository.findByEmail(estudiante.getEmail())).thenReturn(Optional.of(estudiante));
        assertThatThrownBy(() -> service.guardar(estudiante))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Ya existe un estudiante con el email");
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Actualizar estudiante correctamente")
    void actualizar_correctamente() {
        Estudiante existente = crearEstudiante();
        Estudiante actualizado = new Estudiante(1L, "21.111.111-1", "Pedro Soto", "pedro@gmail.com", "987654321", LocalDate.of(2001,1,1), LocalDate.now());
        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        Estudiante resultado = service.actualizar(1L, actualizado);
        assertThat(resultado.getNombre()).isEqualTo("Pedro Soto");
        assertThat(resultado.getEmail()).isEqualTo("pedro@gmail.com");
        verify(repository).save(existente);
    }

    @Test
    @DisplayName("Actualizar estudiante inexistente")
    void actualizar_inexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        Estudiante resultado = service.actualizar(99L, crearEstudiante());
        assertThat(resultado).isNull();
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Eliminar estudiante correctamente")
    void eliminar_correctamente() {
        Estudiante estudiante = crearEstudiante();
        when(repository.findById(1L)).thenReturn(Optional.of(estudiante));
        Estudiante resultado = service.eliminar(1L);
        assertThat(resultado).isEqualTo(estudiante);
        verify(repository).delete(estudiante);
    }

    @Test
    @DisplayName("Eliminar estudiante inexistente")
    void eliminar_inexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        Estudiante resultado = service.eliminar(99L);
        assertThat(resultado).isNull();
        verify(repository, never()).delete(any());
    }
}