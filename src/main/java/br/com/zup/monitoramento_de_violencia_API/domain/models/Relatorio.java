package br.com.zup.monitoramento_de_violencia_API.domain.models;

import java.util.Map;

public class Relatorio {
    private Map<String, Double> distribuicaoPorcentagemTipoIncidente;
    private Map<String, Long> distribuicaoRacaVitima;
    private Map<String, Long> distribuicaoReligiaoVitima;
    private Long totalIncidentes;

    public Relatorio(Map<String, Double> distribuicaoPorcentagemTipoIncidente, Map<String, Long> distribuicaoRacaVitima, Map<String, Long> distribuicaoReligiaoVitima, Long totalIncidentes) {
        this.distribuicaoPorcentagemTipoIncidente = distribuicaoPorcentagemTipoIncidente;
        this.distribuicaoRacaVitima = distribuicaoRacaVitima;
        this.distribuicaoReligiaoVitima = distribuicaoReligiaoVitima;
        this.totalIncidentes = totalIncidentes;
    }

    public Map<String, Double> getDistribuicaoPorcentagemTipoIncidente() {
        return distribuicaoPorcentagemTipoIncidente;
    }

    public void setDistribuicaoPorcentagemTipoIncidente(Map<String, Double> distribuicaoPorcentagemTipoIncidente) {
        this.distribuicaoPorcentagemTipoIncidente = distribuicaoPorcentagemTipoIncidente;
    }

    public Map<String, Long> getDistribuicaoRacaVitima() {
        return distribuicaoRacaVitima;
    }

    public void setDistribuicaoRacaVitima(Map<String, Long> distribuicaoRacaVitima) {
        this.distribuicaoRacaVitima = distribuicaoRacaVitima;
    }

    public Map<String, Long> getDistribuicaoReligiaoVitima() {
        return distribuicaoReligiaoVitima;
    }

    public void setDistribuicaoReligiaoVitima(Map<String, Long> distribuicaoReligiaoVitima) {
        this.distribuicaoReligiaoVitima = distribuicaoReligiaoVitima;
    }

    public Long getTotalIncidentes() {
        return totalIncidentes;
    }

    public void setTotalIncidentes(Long totalIncidentes) {
        this.totalIncidentes = totalIncidentes;
    }
}
