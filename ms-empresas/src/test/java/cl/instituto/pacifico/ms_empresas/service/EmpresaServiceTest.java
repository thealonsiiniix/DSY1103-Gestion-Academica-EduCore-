package cl.instituto.pacifico.ms_empresas.service;
import cl.instituto.pacifico.ms_empresas.exception.BusinessException;
import cl.instituto.pacifico.ms_empresas.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_empresas.model.Empresa;
import cl.instituto.pacifico.ms_empresas.repository.EmpresaRepository;
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
class EmpresaServiceTest {
    @Mock
    private EmpresaRepository repository;
    @InjectMocks
    private EmpresaService service;

    private Empresa crearEmpresa() {
        return new Empresa(1L, "Banco Estado", "76.123.456-7", "Puerto Montt", "+56912345678", "contacto@empresa.cl", true);
    }

    @Test
    @DisplayName("Debe listar todas las empresas")
    void listar_debeRetornarEmpresas() {
        List<Empresa> empresas = List.of(crearEmpresa());
        when(repository.findAll()).thenReturn(empresas);
        List<Empresa> resultado = service.listar();
        assertThat(resultado).hasSize(1);
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Debe buscar empresa por ID")
    void buscarPorId_existente() {
        Empresa empresa = crearEmpresa();
        when(repository.findById(1L)).thenReturn(Optional.of(empresa));
        Empresa resultado = service.buscarPorId(1L);
        assertThat(resultado.getNombre()).isEqualTo("Banco Estado");
    }

    @Test
    @DisplayName("Debe lanzar excepcion cuando ID no existe")
    void buscarPorId_noExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.buscarPorId(99L)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Debe buscar empresa por RUT")
    void buscarPorRut_existente() {
        Empresa empresa = crearEmpresa();
        when(repository.findByRut("76.123.456-7")).thenReturn(Optional.of(empresa));
        Empresa resultado = service.buscarPorRut("76.123.456-7");
        assertThat(resultado.getRut()).isEqualTo("76.123.456-7");
    }

    @Test
    @DisplayName("Debe lanzar excepcion cuando RUT no existe")
    void buscarPorRut_noExiste() {
        when(repository.findByRut("1-9")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.buscarPorRut("1-9"))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Debe crear empresa correctamente")
    void crear_empresaCorrectamente() {
        Empresa empresa = crearEmpresa();
        when(repository.findByRut(empresa.getRut())).thenReturn(Optional.empty());
        when(repository.findByEmail(empresa.getEmail())).thenReturn(Optional.empty());
        when(repository.save(empresa)).thenReturn(empresa);
        Empresa resultado = service.crear(empresa);
        assertThat(resultado.getNombre()).isEqualTo("Banco Estado");
        verify(repository).save(empresa);
    }

    @Test
    @DisplayName("Debe lanzar excepcion si el RUT ya existe")
    void crear_rutDuplicado() {
        Empresa empresa = crearEmpresa();
        when(repository.findByRut(empresa.getRut())).thenReturn(Optional.of(empresa));
        assertThatThrownBy(() -> service.crear(empresa))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("rut");
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si el email ya existe")
    void crear_emailDuplicado() {
        Empresa empresa = crearEmpresa();
        when(repository.findByRut(empresa.getRut())).thenReturn(Optional.empty());
        when(repository.findByEmail(empresa.getEmail())).thenReturn(Optional.of(empresa));
        assertThatThrownBy(() -> service.crear(empresa))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("email");
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Debe actualizar empresa correctamente")
    void actualizar_empresaCorrectamente() {
        Empresa actual = crearEmpresa();
        Empresa nueva = new Empresa(1L, "Banco Chile", "76.123.456-7", "Santiago", "+56999999999", "nuevo@empresa.cl", true);
        when(repository.findById(1L)).thenReturn(Optional.of(actual));
        when(repository.findByRut(nueva.getRut())).thenReturn(Optional.of(actual));
        when(repository.findByEmail(nueva.getEmail())).thenReturn(Optional.empty());
        when(repository.save(any(Empresa.class))).thenAnswer(inv -> inv.getArgument(0));
        Empresa resultado = service.actualizar(1L, nueva);
        assertThat(resultado.getNombre()).isEqualTo("Banco Chile");
    }

    @Test
    @DisplayName("Debe eliminar empresa correctamente")
    void eliminar_empresaCorrectamente() {
        Empresa empresa = crearEmpresa();
        when(repository.findById(1L)).thenReturn(Optional.of(empresa));
        service.eliminar(1L);
        verify(repository).delete(empresa);
    }
}