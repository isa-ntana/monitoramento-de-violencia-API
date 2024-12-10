package br.com.zup.monitoramento_de_violencia_API.app.infra.implement;

import br.com.zup.monitoramento_de_violencia_API.app.infra.IncidenteJpaRepository;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Incidente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IncidenteRepositoryImplement {
    private final IncidenteJpaRepository incidenteJpaRepository;

    public IncidenteRepositoryImplement(IncidenteJpaRepository incidenteJpaRepository) {
        this.incidenteJpaRepository = incidenteJpaRepository;
    }

    public List<Incidente> findAll() {
        return incidenteJpaRepository.findAll();
    }
}
