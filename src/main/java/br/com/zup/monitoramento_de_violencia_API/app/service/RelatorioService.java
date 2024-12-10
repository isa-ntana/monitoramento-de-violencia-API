package br.com.zup.monitoramento_de_violencia_API.app.service;

import br.com.zup.monitoramento_de_violencia_API.app.infra.implement.IncidenteRepositoryImplement;
import br.com.zup.monitoramento_de_violencia_API.domain.dtos.RelatorioDTO;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Incidente;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RelatorioService {

    private final IncidenteRepositoryImplement incidenteRepository;

    public RelatorioService(IncidenteRepositoryImplement incidenteRepository) {
        this.incidenteRepository = incidenteRepository;
    }

    public RelatorioDTO gerarRelatorio() {
        List<Incidente> incidentes = incidenteRepository.findAll();
        long totalIncidentes = incidentes.size();

        Map<String, Long> distribuicaoTipoIncidente = new HashMap<>();
        Map<String, Long> distribuicaoRacaVitima = new HashMap<>();
        Map<String, Long> distribuicaoReligiaoVitima = new HashMap<>();

        for (Incidente incidente : incidentes) {
            String tipoIncidente = incidente.getTipoIncidente();
            distribuicaoTipoIncidente.put(tipoIncidente, distribuicaoTipoIncidente.getOrDefault(tipoIncidente, 0L) + 1);

            String racaVitima = incidente.getVitima().getRaca();
            if (racaVitima.equalsIgnoreCase("Negra") || racaVitima.equalsIgnoreCase("Parda") || racaVitima.equalsIgnoreCase("Preta")) {
                distribuicaoRacaVitima.put(racaVitima, distribuicaoRacaVitima.getOrDefault(racaVitima, 0L) + 1);
            } else {
                distribuicaoRacaVitima.put("Não Negros", distribuicaoRacaVitima.getOrDefault("Não Negros", 0L) + 1);
            }

            String religiaoVitima = incidente.getVitima().getReligiao();
            distribuicaoReligiaoVitima.put(religiaoVitima, distribuicaoReligiaoVitima.getOrDefault(religiaoVitima, 0L) + 1);
        }

        Map<String, Double> distribuicaoPorcentagemTipoIncidente = new HashMap<>();
        for (Map.Entry<String, Long> entry : distribuicaoTipoIncidente.entrySet()) {
            double percentual = (double) entry.getValue() / totalIncidentes * 100;
            distribuicaoPorcentagemTipoIncidente.put(entry.getKey(), percentual);
        }

        return new RelatorioDTO(distribuicaoPorcentagemTipoIncidente, distribuicaoRacaVitima, distribuicaoReligiaoVitima, totalIncidentes);
    }
}
