package br.com.zup.monitoramento_de_violencia_API.RelatorioTestes;

import br.com.zup.monitoramento_de_violencia_API.app.service.RelatorioService;
import br.com.zup.monitoramento_de_violencia_API.app.infra.implement.IncidenteRepositoryImplement;
import br.com.zup.monitoramento_de_violencia_API.domain.dtos.RelatorioDTO;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Incidente;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Vitima;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RelatorioServiceTest {

    @InjectMocks
    private RelatorioService relatorioService;

    @Mock
    private IncidenteRepositoryImplement incidenteRepository;

    private List<Incidente> incidentes;

    @BeforeEach
    void setUp() {
        Vitima vitima1 = new Vitima(1L, "Maria Oliveira", 25, "Feminino", "Negra", "Evangélica");
        Vitima vitima2 = new Vitima(2L, "João Silva", 30, "Masculino", "Branca", "Católica");
        Vitima vitima3 = new Vitima(3L, "Ana Souza", 22, "Feminino", "Parda", "Religião de matriz africana");

        Incidente incidente1 = new Incidente(1L, vitima1, "Violência Racial", "Descrição", "Rua X, 123", LocalDate.now());
        Incidente incidente2 = new Incidente(2L, vitima2, "Violência Religiosa", "Descrição", "Rua Y, 456", LocalDate.now());
        Incidente incidente3 = new Incidente(3L, vitima3, "Violência Racial", "Descrição", "Rua Z, 789", LocalDate.now());

        incidentes = List.of(incidente1, incidente2, incidente3);
    }

    @Test
    void gerarRelatorioComSucesso() {
        when(incidenteRepository.findAll()).thenReturn(incidentes);

        RelatorioDTO relatorio = relatorioService.gerarRelatorio();
        assertNotNull(relatorio);
        assertEquals(3, relatorio.getTotalIncidentes());

        Map<String, Long> distribuicaoRacaVitima = relatorio.getDistribuicaoRacaVitima();
        assertTrue(distribuicaoRacaVitima.containsKey("Negra"));
        assertTrue(distribuicaoRacaVitima.containsKey("Parda"));
        assertTrue(distribuicaoRacaVitima.containsKey("Não Negros"));

        assertEquals(1, distribuicaoRacaVitima.get("Não Negros"));
    }
}
