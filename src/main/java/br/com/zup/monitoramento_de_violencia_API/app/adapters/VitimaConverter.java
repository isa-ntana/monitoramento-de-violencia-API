package br.com.zup.monitoramento_de_violencia_API.app.adapters;

import br.com.zup.monitoramento_de_violencia_API.app.modelsapp.VitimaEntity;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Vitima;


public class VitimaConverter {
    public static VitimaEntity toEntity(Vitima vitima) {


        return new VitimaEntity(
                vitima.getId(),
                vitima.getNome(),
                vitima.getIdade(),
                vitima.getGenero(),
                vitima.getRaca(),
                vitima.getReligiao()
        );
    }

    public static Vitima toDomain(VitimaEntity vitimaEntity){

        return new Vitima(
                vitimaEntity.getId(),
                vitimaEntity.getNome(),
                vitimaEntity.getGenero(),
                vitimaEntity.getRaca(),
                vitimaEntity.getReligiao()
        );
    }

}
