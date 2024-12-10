package br.com.zup.monitoramento_de_violencia_API.domain.usercases.implement;

import br.com.zup.monitoramento_de_violencia_API.app.infra.implement.IncidenteRepositoryImplement;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Incidente;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Relatorio;
import br.com.zup.monitoramento_de_violencia_API.domain.usercases.GerarRelatorio;

import java.util.*;

public class GerarRelatorioImplement implements GerarRelatorio {
    private final IncidenteRepositoryImplement incidenteRepository;

    public GerarRelatorioImplement(IncidenteRepositoryImplement incidenteRepository) {
        this.incidenteRepository = incidenteRepository;
    }

    @Override
    public Relatorio gerarRelatorio() {
        List<Incidente> incidentes = incidenteRepository.findAll();

        Map<String, Double> tipoIncidenteDistribuicao = calcularDistribuicaoPorTipoIncidente(incidentes);

        Map<String, Long> racaVitimaDistribuicao = calcularDistribuicaoPorRaca(incidentes);
        Map<String, Long> religiaoVitimaDistribuicao = calcularDistribuicaoPorReligiao(incidentes);

        Long totalIncidentes = calcularTotalIncidentes(incidentes);

        Double porcentagemVitimiasNegras = calcularPorcentagemVitimiasNegras(racaVitimaDistribuicao, totalIncidentes);
        Double porcentagemReligiaoMatrizAfricana = calcularPorcentagemVitimiasReligiosasAfricana(religiaoVitimaDistribuicao, totalIncidentes);

        return new Relatorio(tipoIncidenteDistribuicao, racaVitimaDistribuicao, porcentagemVitimiasNegras, religiaoVitimaDistribuicao, porcentagemReligiaoMatrizAfricana, totalIncidentes);
    }

    private Map<String, Double> calcularDistribuicaoPorTipoIncidente(List<Incidente> incidentes) {
        Map<String, Long> tipoIncidenteCount = new HashMap<>();
        for (Incidente incidente : incidentes) {
            tipoIncidenteCount.put(incidente.getTipoIncidente(),
                    tipoIncidenteCount.getOrDefault(
                            incidente.getTipoIncidente(), 0L) + 1);
        }

        Map<String, Double> tipoIncidenteDistribuicao = new HashMap<>();
        long total = incidentes.size();
        for (Map.Entry<String, Long> entry : tipoIncidenteCount.entrySet()) {
            tipoIncidenteDistribuicao.put(entry.getKey(),
                    (entry.getValue() * 100.0) / total);
        }

        return tipoIncidenteDistribuicao;
    }

    public Map<String, Long> calcularDistribuicaoPorRaca(List<Incidente> incidentes) {
        Map<String, Long> racaContador = new HashMap<>();
        for (Incidente incidente : incidentes) {
            String raca = incidente.getVitima().getRaca().toLowerCase();

            if (raca.contains("negro") || raca.contains("preto") || raca.contains("pardo")) {
                racaContador.put("Negra", racaContador.getOrDefault("Negra", 0L) + 1);
            } else {
                racaContador.put(raca, racaContador.getOrDefault(raca, 0L) + 1);
            }
        }
        return racaContador;
    }

    public Map<String, Long> calcularDistribuicaoPorReligiao(List<Incidente> incidentes) {
        Map<String, Long> religiaoContador = new HashMap<>();
        for (Incidente incidente : incidentes) {
            String religiao = incidente.getVitima().getReligiao().toLowerCase();

            if (religiao.contains("umbanda") || religiao.contains("candomblé")) {
                religiaoContador.put("Matriz Africana",
                        religiaoContador.getOrDefault("Matriz Africana", 0L) + 1);
            } else {
                religiaoContador.put(religiao,
                        religiaoContador.getOrDefault(religiao, 0L) + 1);
            }
        }
        return religiaoContador;
    }

    public Double calcularPorcentagemVitimiasNegras(Map<String, Long> racaVitimaDistribuicao, Long totalIncidentes) {
        Long totalVitimiasNegras = racaVitimaDistribuicao
                .getOrDefault("Negra", 0L);
        return (totalVitimiasNegras * 100.0) / totalIncidentes;
    }

    public Double calcularPorcentagemVitimiasReligiosasAfricana(Map<String, Long> religiaoVitimaDistribuicao, Long totalIncidentes) {
        Long totalVitimiasReligiosasAfricana = religiaoVitimaDistribuicao
                .getOrDefault("Matriz Africana", 0L);
        return (totalVitimiasReligiosasAfricana * 100.0) / totalIncidentes;
    }

    private Long calcularTotalIncidentes(List<Incidente> incidentes) {
        return (long) incidentes.size();
    }
}
