package br.com.zup.monitoramento_de_violencia_API.domain.dtos;

public class IncidenteAnaliticoDTO {
    //conterá as porcentagens e quantidades totais de vitimas por categoria
    private Long totalIncidentes;
    private Long vitimasNegras;
    private Double porcentagemVitimasNegras;
    private Long vitimasReligiosasAfricana;
    private Double porcentagemVitimasReligiosasAfricana;

    public IncidenteAnaliticoDTO(Long totalIncidentes, Long vitimasNegras, Double porcentagemVitimasNegras, Long vitimasReligiosasAfricana, Double porcentagemVitimasReligiosasAfricana) {
        this.totalIncidentes = totalIncidentes;
        this.vitimasNegras = vitimasNegras;
        this.porcentagemVitimasNegras = porcentagemVitimasNegras;
        this.vitimasReligiosasAfricana = vitimasReligiosasAfricana;
        this.porcentagemVitimasReligiosasAfricana = porcentagemVitimasReligiosasAfricana;
    }

    public Long getTotalIncidentes() {
        return totalIncidentes;
    }

    public void setTotalIncidentes(Long totalIncidentes) {
        this.totalIncidentes = totalIncidentes;
    }

    public Long getVitimasNegras() {
        return vitimasNegras;
    }

    public void setVitimasNegras(Long vitimasNegras) {
        this.vitimasNegras = vitimasNegras;
    }

    public Double getPorcentagemVitimasNegras() {
        return porcentagemVitimasNegras;
    }

    public void setPorcentagemVitimasNegras(Double porcentagemVitimasNegras) {
        this.porcentagemVitimasNegras = porcentagemVitimasNegras;
    }

    public Long getVitimasReligiosasAfricana() {
        return vitimasReligiosasAfricana;
    }

    public void setVitimasReligiosasAfricana(Long vitimasReligiosasAfricana) {
        this.vitimasReligiosasAfricana = vitimasReligiosasAfricana;
    }

    public Double getPorcentagemVitimasReligiosasAfricana() {
        return porcentagemVitimasReligiosasAfricana;
    }

    public void setPorcentagemVitimasReligiosasAfricana(Double porcentagemVitimasReligiosasAfricana) {
        this.porcentagemVitimasReligiosasAfricana = porcentagemVitimasReligiosasAfricana;
    }
}
