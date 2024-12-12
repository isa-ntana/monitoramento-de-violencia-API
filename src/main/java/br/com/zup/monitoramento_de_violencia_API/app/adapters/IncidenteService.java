package br.com.zup.monitoramento_de_violencia_API.app.adapters;

import br.com.zup.monitoramento_de_violencia_API.app.infra.IncidenteJpaRepository;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Incidente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenteService {
    private final IncidenteJpaRepository incidenteJpaRepository;

    public IncidenteService(IncidenteJpaRepository incidenteJpaRepository) {
        this.incidenteJpaRepository = incidenteJpaRepository;
    }

    public Incidente salvarIncidente(Incidente incidente) {
        return incidenteJpaRepository.save(incidente);
    }

    public Iterable<Incidente> listarIncidentes() {
        return incidenteJpaRepository.findAll();
    }

    public List<Incidente> buscarIncidentePorNomeVitima(String nomeVitima) {
        return incidenteJpaRepository.findByVitimaNome(nomeVitima);
    }

    public void deletarIncidente(String id) {
        Optional<Incidente> incidenteOptional = incidenteJpaRepository.findById(id);
        if (incidenteOptional.isEmpty()) {
            throw new RuntimeException("Incidente não encontrado com o ID: " + id);
        }
        incidenteJpaRepository.delete(incidenteOptional.get());
    }
}
