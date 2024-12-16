package br.com.zup.monitoramento_de_violencia_API.app.modelsapp;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
    @Table (name = "vitimas")
    public class VitimaEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private String id;
        private String nome;
        private int idade;
        private String genero;
        private String raca;
        private String religiao;

        @OneToMany(mappedBy = "vitima", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<IncidenteEntity> incidentes;

        public VitimaEntity() {
        }

    public VitimaEntity(String id, String nome, int idade, String genero, String raca, String religiao) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.raca = raca;
        this.religiao = religiao;
        this.incidentes = incidentes;
    }

    public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIdade() {
            return idade;
        }

        public void setIdade(int idade) {
            this.idade = idade;
        }

        public List<IncidenteEntity> getIncidentes() {
            return incidentes;
        }

        public void setIncidentes(List<IncidenteEntity> incidentes) {
            this.incidentes = incidentes;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
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
    }