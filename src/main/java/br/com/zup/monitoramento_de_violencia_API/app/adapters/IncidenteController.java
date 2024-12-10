package br.com.zup.monitoramento_de_violencia_API.app.adapters;

import br.com.zup.monitoramento_de_violencia_API.app.service.IncidenteService;
import br.com.zup.monitoramento_de_violencia_API.domain.models.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidentes")
public class IncidenteController {

    @Autowired
    private IncidenteService incidenteService;

    @GetMapping
    public ResponseEntity<?> getAllIncidentes() {
        try {
            List<Incidente> incidentes = (List<Incidente>) incidenteService.listarIncidentes();
            return ResponseEntity.status(200).body(incidentes);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Não existem incidentes cadastrados");
        }
    }

    @PostMapping
    public ResponseEntity<?> createIncidente(@RequestBody Incidente incidente) {
        try {
            Incidente incidenteSalvo = incidenteService.salvarIncidente(incidente);
            return ResponseEntity.status(201).body(incidenteSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Não foi possível cadastrar o incidente");
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity<?> getIncidenteByNomeVitima(@PathVariable String nome) {
        try {
            List<Incidente> incidentes = incidenteService.buscarIncidentePorNomeVitima(nome);
            return ResponseEntity.status(200).body(incidentes);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Nome não encontrado no banco de dados");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncidenteById(@PathVariable Long id) {
        try {
            incidenteService.deletarIncidente(id);
            return ResponseEntity.status(204).body("Incidente deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Incidente não encontrado com o ID fornecido");
        }
    }
}
