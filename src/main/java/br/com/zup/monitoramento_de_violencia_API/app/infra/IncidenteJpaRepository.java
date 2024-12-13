package br.com.zup.monitoramento_de_violencia_API.app.infra;

import br.com.zup.monitoramento_de_violencia_API.domain.models.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenteJpaRepository extends JpaRepository<Incidente, String> {
    List<Incidente> findByVitimaNome(String nomeVitima);
}
