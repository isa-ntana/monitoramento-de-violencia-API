package br.com.zup.monitoramento_de_violencia_API.domain.models;

import java.time.LocalDate;

public class Incidente {
    private Long id;
    private Vitima vitima;
    private String tipoIncidente;
    private String descricao;
    private String local;
    private LocalDate dataIncidente;

    public Incidente(Long id, Vitima vitima, String tipoIncidente, String descricao, String local, LocalDate dataIncidente) {
        this.id = id;
        this.vitima = vitima;
        this.tipoIncidente = tipoIncidente;
        this.descricao = descricao;
        this.local = local;
        this.dataIncidente = dataIncidente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vitima getVitima() {
        return vitima;
    }

    public void setVitima(Vitima vitima) {
        this.vitima = vitima;
    }

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getDataIncidente() {
        return dataIncidente;
    }

    public void setDataIncidente(LocalDate dataIncidente) {
        this.dataIncidente = dataIncidente;
    }
}
