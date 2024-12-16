package br.com.zup.monitoramento_de_violencia_API.app.modelsapp;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
    @Table (name = "incidente")
    public class IncidenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne
    @JoinColumn(name = "vitima_id", nullable = false)
    private VitimaEntity vitima;

    private String tipoIncidente;
    private String descricao;
    private String local;
    private LocalDate dataIncidente;

    public IncidenteEntity(){}

    public IncidenteEntity(String id, String descricao, String local) {
        this.id = UUID.randomUUID().toString();
        this.descricao = descricao;
        this.local = local;
    }

    public LocalDate getDataIncidente() {
        return dataIncidente;
    }

    public void setDataIncidente(LocalDate dataIncidente) {
        this.dataIncidente = dataIncidente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public VitimaEntity getVitima() {
        return vitima;
    }

    public void setVitima(VitimaEntity vitima) {
        this.vitima = vitima;
    }
}
