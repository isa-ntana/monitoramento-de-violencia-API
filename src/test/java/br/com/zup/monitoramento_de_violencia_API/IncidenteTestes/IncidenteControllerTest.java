package br.com.zup.monitoramento_de_violencia_API.IncidenteTestes;

import br.com.zup.monitoramento_de_violencia_API.app.modelsapp.VitimaEntity;
import br.com.zup.monitoramento_de_violencia_API.app.ports.IncidenteController;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Incidente;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Vitima;
import br.com.zup.monitoramento_de_violencia_API.app.adapters.IncidenteService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IncidenteControllerTest {

    @InjectMocks
    private IncidenteController incidenteController;

    @Mock
    private IncidenteService incidenteService;

    private Incidente incidente;

    @BeforeEach
    void setUp() {
        VitimaEntity vitimaEntity = new VitimaEntity();
        Vitima vitima = new Vitima(
                "Maria Oliveira",
                vitimaEntity.getNome(),
                "Feminine",
                "Negra",
                "Evangélica");
        incidente = new Incidente(
                vitima,
                "Incidente Teste",
                "Descrição do incidente",
                "Rua X, 123",
                LocalDate.now());
    }

    @Test
    void testarCriarIncidente() {
        when(incidenteService.salvarIncidente(any(Incidente.class))).thenReturn(incidente);

        ResponseEntity<?> response = incidenteController.createIncidente(incidente);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Incidente incidenteSalvo = (Incidente) response.getBody();
        assertNotNull(incidenteSalvo);
        assertEquals("Incidente Teste", incidenteSalvo.getTipoIncidente());
    }

    @Test
    void testarDeletarIncidente() {
        doNothing().when(incidenteService).deletarIncidente(String.valueOf(1L));

        ResponseEntity<?> response = incidenteController.deleteIncidenteById(String.valueOf(1L));
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Incidente deletado com sucesso", response.getBody());
    }

    @Test
    void testarDeletarCasoNaoEncontrado() {
        doThrow(new RuntimeException("Incidente não encontrado com o ID fornecido"))
                .when(incidenteService).deletarIncidente(String.valueOf(1L));

        ResponseEntity<?> response = incidenteController.deleteIncidenteById(String.valueOf(1L));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Incidente não encontrado com o ID fornecido", response.getBody());
    }

    @Test
    void buscarTodosOsIncidentes() {
        when(incidenteService.listarIncidentes()).thenReturn(List.of(incidente));

        ResponseEntity<?> response = incidenteController.getAllIncidentes();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Incidente> incidentes = (List<Incidente>) response.getBody();
        assertNotNull(incidentes);
        assertFalse(incidentes.isEmpty());
        assertEquals("Incidente Teste", incidentes.get(0).getTipoIncidente());
    }

    @Test
    void listarIncidentesPeloNomeVitima() {
        String nomeVitima = "Maria Oliveira";

        List<Incidente> incidentes = List.of(incidente);
        when(incidenteService.buscarIncidentePorNomeVitima(nomeVitima)).thenReturn(incidentes);

        ResponseEntity<?> response = incidenteController.getIncidenteByNomeVitima(nomeVitima);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Incidente> listaDeIncidentes = (List<Incidente>) response.getBody();
        assertNotNull(listaDeIncidentes);
        assertFalse(listaDeIncidentes.isEmpty());
        assertEquals("Incidente Teste", listaDeIncidentes.get(0).getTipoIncidente());
    }
}
