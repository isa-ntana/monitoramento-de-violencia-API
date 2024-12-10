package br.com.zup.monitoramento_de_violencia_API.app.adapters;

import br.com.zup.monitoramento_de_violencia_API.app.service.RelatorioService;
import br.com.zup.monitoramento_de_violencia_API.domain.dtos.RelatorioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping
    public ResponseEntity<?> gerarRelatorio() {
        try {
            RelatorioDTO relatorio = relatorioService.gerarRelatorio();
            return ResponseEntity.ok(relatorio);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao gerar o relatório");
        }
    }
}

