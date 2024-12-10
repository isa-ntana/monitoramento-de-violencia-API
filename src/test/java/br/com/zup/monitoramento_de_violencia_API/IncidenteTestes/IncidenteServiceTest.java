package br.com.zup.monitoramento_de_violencia_API.IncidenteTestes;

import br.com.zup.monitoramento_de_violencia_API.app.infra.IncidenteJpaRepository;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Incidente;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Vitima;
import br.com.zup.monitoramento_de_violencia_API.app.service.IncidenteService;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class IncidenteServiceTest {

    private IncidenteService incidenteService;
    private IncidenteJpaRepository incidenteJpaRepository;

    private Vitima vitima;
    private Incidente incidente;

    @BeforeEach
    void setUp() {
        incidenteJpaRepository = mock(IncidenteJpaRepository.class);
        incidenteService = new IncidenteService(incidenteJpaRepository);

        vitima = new Vitima(
                1L,
                "Maria Oliveira",
                25,
                "Feminino",
                "Negra",
                "Evangélica"
        );

        incidente = new Incidente(
                1L,
                vitima,
                "Incidente Teste",
                "Descrição do incidente: agressão física com lesões leves.",
                "Rua X, 123, Bairro Y",
                LocalDate.of(2024, 12, 9)
        );
    }

    @Test
    void salvarIncidente() {
        when(incidenteJpaRepository.save(any(Incidente.class))).thenReturn(incidente);

        Incidente incidenteSalvo = incidenteService.salvarIncidente(incidente);
        assertNotNull(incidenteSalvo);
        assertEquals(1L, incidenteSalvo.getId());
        assertEquals("Incidente Teste", incidenteSalvo.getTipoIncidente());

        verify(incidenteJpaRepository, times(1)).save(incidente);
    }

    @Test
    void listarIncidentes() {
        Incidente incidente2 = new Incidente(2L, vitima, "Incidente Teste 2", "Descrição do incidente 2", "Rua Y", LocalDate.of(2024, 12, 10));

        when(incidenteJpaRepository.findAll()).thenReturn(Arrays.asList(incidente, incidente2));

        Iterable<Incidente> incidentes = incidenteService.listarIncidentes();
        assertNotNull(incidentes);
        assertEquals(2, ((List<Incidente>) incidentes).size());
        verify(incidenteJpaRepository, times(1)).findAll();
    }

    @Test
    void buscarIncidentePorNomeVitima() {
        when(incidenteJpaRepository.findByVitimaNome("Maria Oliveira"))
                .thenReturn(Arrays.asList(incidente));

        List<Incidente> incidentes = incidenteService.buscarIncidentePorNomeVitima("Maria Oliveira");
        assertNotNull(incidentes);
        assertEquals(1, incidentes.size());
        assertEquals("Maria Oliveira", incidentes.get(0).getVitima().getNome());

        verify(incidenteJpaRepository, times(1)).findByVitimaNome("Maria Oliveira");
    }

    @Test
    void testarDeletarIncidente() {
        when(incidenteJpaRepository.findById(1L)).thenReturn(Optional.of(incidente));
        doNothing().when(incidenteJpaRepository).delete(incidente);

        incidenteService.deletarIncidente(1L);
        verify(incidenteJpaRepository, times(1)).delete(incidente);
    }

    @Test
    void testarDeletarIncidenteNaoEncontrado() {
        when(incidenteJpaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> incidenteService.deletarIncidente(1L));
        assertEquals("Incidente não encontrado com o ID: 1", exception.getMessage());

        verify(incidenteJpaRepository, times(0)).delete(any(Incidente.class));
    }
}
