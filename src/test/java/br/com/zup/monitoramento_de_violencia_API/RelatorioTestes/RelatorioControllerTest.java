package br.com.zup.monitoramento_de_violencia_API.RelatorioTestes;

import br.com.zup.monitoramento_de_violencia_API.app.adapters.RelatorioController;
import br.com.zup.monitoramento_de_violencia_API.app.service.RelatorioService;
import br.com.zup.monitoramento_de_violencia_API.domain.dtos.RelatorioDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RelatorioControllerTest {

    @InjectMocks
    private RelatorioController relatorioController;

    @Mock
    private RelatorioService relatorioService;

    private RelatorioDTO relatorioDTO;

    @BeforeEach
    void setUp() {
        relatorioDTO = new RelatorioDTO(
                Map.of("violenciaRacial", 60.0, "violenciaReligiosa", 40.0),
                Map.of("negra", 200L, "nãoNegra", 300L),
                Map.of("religiosaAfricana", 150L, "outrasReligiões", 350L),
                500L
        );
    }

    @Test
    void gerarRelatorio_Sucesso() {
        when(relatorioService.gerarRelatorio()).thenReturn(relatorioDTO);

        ResponseEntity<?> response = relatorioController.gerarRelatorio();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        RelatorioDTO relatorio = (RelatorioDTO) response.getBody();
        assertNotNull(relatorio);
        assertEquals(500L, relatorio.getTotalIncidentes());
        assertTrue(relatorio.getDistribuicaoPorcentagemTipoIncidente().containsKey("violenciaRacial"));
        assertTrue(relatorio.getDistribuicaoRacaVitima().containsKey("negra"));
        assertTrue(relatorio.getDistribuicaoReligiaoVitima().containsKey("religiosaAfricana"));
    }

    @Test
    void gerarRelatorio_Erro() {
        when(relatorioService.gerarRelatorio()).thenThrow(new RuntimeException("Erro interno ao gerar relatório"));

        ResponseEntity<?> response = relatorioController.gerarRelatorio();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno ao gerar o relatório", response.getBody());
    }
}
