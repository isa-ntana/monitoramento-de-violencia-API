package br.com.zup.monitoramento_de_violencia_API.domain.dtos;

import java.time.LocalDate;

public class RegistrarIncidenteDTO {
    private Long idVitima;
    private String nomeVitima;
    private int idade;
    private String genero;
    private String endereco;
    private String raca;
    private String religiao;
    private String tipoIncidente;
    private LocalDate dataIncidente;
    private String localIncidente;
    private String descricaoIncidente;

    public RegistrarIncidenteDTO(Long idVitima, String nomeVitima, int idade, String genero, String endereco, String raca, String religiao, String tipoIncidente, LocalDate dataIncidente, String localIncidente, String descricaoIncidente) {
        this.idVitima = idVitima;
        this.nomeVitima = nomeVitima;
        this.idade = idade;
        this.genero = genero;
        this.endereco = endereco;
        this.raca = raca;
        this.religiao = religiao;
        this.tipoIncidente = tipoIncidente;
        this.dataIncidente = dataIncidente;
        this.localIncidente = localIncidente;
        this.descricaoIncidente = descricaoIncidente;
    }

    public long getIdVitima() {
        return idVitima;
    }

    public void setIdVitima(long idVitima) {
        this.idVitima = idVitima;
    }

    public String getNomeVitima() {
        return nomeVitima;
    }

    public void setNomeVitima(String nomeVitima) {
        this.nomeVitima = nomeVitima;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getReligiao() {
        return religiao;
    }

    public void setReligiao(String religiao) {
        this.religiao = religiao;
    }

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public LocalDate getDataIncidente() {
        return dataIncidente;
    }

    public void setDataIncidente(LocalDate dataIncidente) {
        this.dataIncidente = dataIncidente;
    }

    public String getLocalIncidente() {
        return localIncidente;
    }

    public void setLocalIncidente(String localIncidente) {
        this.localIncidente = localIncidente;
    }

    public String getDescricaoIncidente() {
        return descricaoIncidente;
    }

    public void setDescricaoIncidente(String descricaoIncidente) {
        this.descricaoIncidente = descricaoIncidente;
    }
}
