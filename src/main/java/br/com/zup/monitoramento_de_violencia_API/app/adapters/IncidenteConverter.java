package br.com.zup.monitoramento_de_violencia_API.app.adapters;

import br.com.zup.monitoramento_de_violencia_API.app.modelsapp.IncidenteEntity;
import br.com.zup.monitoramento_de_violencia_API.domain.models.Incidente;

public class IncidenteConverter {
    public static IncidenteEntity toEntity(Incidente incidente) {

        return new IncidenteEntity(
                incidente.getId(),
                incidente.getDescricao(),
                incidente.getLocal()
        );
    }
}