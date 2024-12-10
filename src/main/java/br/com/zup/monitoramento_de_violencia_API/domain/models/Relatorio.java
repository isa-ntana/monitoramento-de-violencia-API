package br.com.zup.monitoramento_de_violencia_API.domain.models;

import java.util.Map;

public class Relatorio {
    private Map<String, Double> distribuicaoPorcentagemTipoIncidente;
    private Map<String, Long> distribuicaoRacaVitima;
    private Double porcentagemVitimiasNegras;
    private Map<String, Long> distribuicaoReligiaoMatrizAfricana;
    private Double porcentagemReligiaoMatrizAfricana;
    private Long totalIncidentes;

    public Relatorio(Map<String, Double> distribuicaoPorcentagemTipoIncidente, Map<String, Long> distribuicaoRacaVitima, Double porcentagemVitimiasNegras, Map<String, Long> distribuicaoReligiaoMatrizAfricana, Double porcentagemReligiaoMatrizAfricana, Long totalIncidentes) {
        this.distribuicaoPorcentagemTipoIncidente = distribuicaoPorcentagemTipoIncidente;
        this.distribuicaoRacaVitima = distribuicaoRacaVitima;
        this.porcentagemVitimiasNegras = porcentagemVitimiasNegras;
        this.distribuicaoReligiaoMatrizAfricana = distribuicaoReligiaoMatrizAfricana;
        this.porcentagemReligiaoMatrizAfricana = porcentagemReligiaoMatrizAfricana;
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

    public Double getPorcentagemVitimiasNegras() {
        return porcentagemVitimiasNegras;
    }

    public void setPorcentagemVitimiasNegras(Double porcentagemVitimiasNegras) {
        this.porcentagemVitimiasNegras = porcentagemVitimiasNegras;
    }

    public Map<String, Long> getDistribuicaoReligiaoMatrizAfricana() {
        return distribuicaoReligiaoMatrizAfricana;
    }

    public void setDistribuicaoReligiaoMatrizAfricana(Map<String, Long> distribuicaoReligiaoMatrizAfricana) {
        this.distribuicaoReligiaoMatrizAfricana = distribuicaoReligiaoMatrizAfricana;
    }

    public Double getPorcentagemReligiaoMatrizAfricana() {
        return porcentagemReligiaoMatrizAfricana;
    }

    public void setPorcentagemReligiaoMatrizAfricana(Double porcentagemReligiaoMatrizAfricana) {
        this.porcentagemReligiaoMatrizAfricana = porcentagemReligiaoMatrizAfricana;
    }

    public Long getTotalIncidentes() {
        return totalIncidentes;
    }

    public void setTotalIncidentes(Long totalIncidentes) {
        this.totalIncidentes = totalIncidentes;
    }
}
