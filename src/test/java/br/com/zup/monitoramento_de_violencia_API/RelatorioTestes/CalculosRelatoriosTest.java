package br.com.zup.monitoramento_de_violencia_API.RelatorioTestes;

import br.com.zup.monitoramento_de_violencia_API.app.infra.implement.IncidenteRepositoryImplement;
import br.com.zup.monitoramento_de_violencia_API.app.modelsapp.VitimaEntity;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Incidente;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Relatorio;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Vitima;
import br.com.zup.monitoramento_de_violencia_API.domain.usercases.implement.GerarRelatorioImplement;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculosRelatoriosTest {

    @Mock
    private IncidenteRepositoryImplement incidenteRepository;

    @InjectMocks
    private GerarRelatorioImplement gerarRelatorioImplement;

    private List<Incidente> incidentes;

    @BeforeEach
    void setUp() {
        VitimaEntity vitimaEntity = new VitimaEntity();
        Vitima vitima1 = new Vitima("João", vitimaEntity.getNome(), "masculino", "preto", "umbanda");
        Vitima vitima2 = new Vitima( "Maria", vitimaEntity.getNome(), "Feminino", "pardo", "candomblé");
        Vitima vitima3 = new Vitima( "Carlos", vitimaEntity.getNome(), "Masculino", "branco", "católica");

        Incidente incidente1 = new Incidente( vitima1, "Violência racial", "Descrição 1", "Local 1", LocalDate.now());
        Incidente incidente2 = new Incidente( vitima2, "Violência religiosa", "Descrição 2", "Local 2", LocalDate.now());
        Incidente incidente3 = new Incidente( vitima3, "Violência racial", "Descrição 3", "Local 3", LocalDate.now());

        incidentes = Arrays.asList(incidente1, incidente2, incidente3);
    }

    @Test
    void testarGerarRelatorio() {
        when(incidenteRepository.findAll()).thenReturn(incidentes);

        Relatorio relatorio = gerarRelatorioImplement.gerarRelatorio();

        assertNotNull(relatorio.getDistribuicaoPorcentagemTipoIncidente());
        assertEquals(2, relatorio.getDistribuicaoPorcentagemTipoIncidente().size());

        assertNotNull(relatorio.getDistribuicaoRacaVitima());
        assertEquals(2, relatorio.getDistribuicaoRacaVitima().size());

        assertNotNull(relatorio.getDistribuicaoReligiaoMatrizAfricana());
        assertEquals(2, relatorio.getDistribuicaoReligiaoMatrizAfricana().size());

        assertNotNull(relatorio.getPorcentagemVitimiasNegras());
        assertEquals(66.67, relatorio.getPorcentagemVitimiasNegras(), 0.01);

        assertNotNull(relatorio.getPorcentagemReligiaoMatrizAfricana());
        assertEquals(66.67, relatorio.getPorcentagemReligiaoMatrizAfricana(), 0.01);

        assertNotNull(relatorio.getTotalIncidentes());
        assertEquals(3L, relatorio.getTotalIncidentes());
    }

    @Test
    void testarCalcularDistribuicaoPorTipoIncidente() {
        when(incidenteRepository.findAll()).thenReturn(incidentes);

        Relatorio relatorio = gerarRelatorioImplement.gerarRelatorio();
        Map<String, Double> tipoIncidenteDistribuicao = relatorio.getDistribuicaoPorcentagemTipoIncidente();
        assertEquals(2, tipoIncidenteDistribuicao.size());
        assertTrue(tipoIncidenteDistribuicao.containsKey("Violência racial"));
        assertTrue(tipoIncidenteDistribuicao.containsKey("Violência religiosa"));
        assertEquals(66.67, tipoIncidenteDistribuicao.get("Violência racial"), 0.01);
        assertEquals(33.33, tipoIncidenteDistribuicao.get("Violência religiosa"), 0.01);
    }

    @Test
    void testarCalcularDistribuicaoPorRaca() {
        when(incidenteRepository.findAll()).thenReturn(incidentes);

        Relatorio relatorio = gerarRelatorioImplement.gerarRelatorio();
        Map<String, Long> racaDistribuicao = relatorio.getDistribuicaoRacaVitima();
        assertTrue(racaDistribuicao.containsKey("Negra"), "A chave 'Negra' não foi encontrada na distribuição de raça.");
        assertEquals(2L, racaDistribuicao.get("Negra"), "A quantidade de vítimas negras está incorreta.");
    }

    @Test
    void testarCalcularPorcentagemVitimiasNegras() {
        Long totalIncidentes = 3L;
        Map<String, Long> racaDistribuicao = Map.of("Negra", 2L, "Pardo", 1L);

        Double porcentagem = gerarRelatorioImplement.calcularPorcentagemVitimiasNegras(racaDistribuicao, totalIncidentes);
        assertEquals(66.67, porcentagem, 0.01);
    }

    @Test
    void testarCalcularDistribuicaoPorReligiao() {
        when(incidenteRepository.findAll()).thenReturn(incidentes);

        Relatorio relatorio = gerarRelatorioImplement.gerarRelatorio();
        Map<String, Long> religiaoDistribuicao = relatorio.getDistribuicaoReligiaoMatrizAfricana();
        assertTrue(religiaoDistribuicao.containsKey("Matriz Africana"));
        assertTrue(religiaoDistribuicao.containsKey("católica"));
        assertEquals(66.67, religiaoDistribuicao.get("Matriz Africana") * 100.0 / 3, 0.01);
        assertEquals(33.33, religiaoDistribuicao.get("católica") * 100.0 / 3, 0.01);
    }

    @Test
    void testarCalcularPorcentagemReligiaoMatrizAfricana() {
        Long totalIncidentes = 3L;
        Map<String, Long> religiaoDistribuicao = Map.of("Matriz Africana", 2L, "Católica", 1L);

        Double porcentagem = gerarRelatorioImplement.calcularPorcentagemVitimiasReligiosasAfricana(religiaoDistribuicao, totalIncidentes);
        assertEquals(66.67, porcentagem, 0.01);
    }
}

