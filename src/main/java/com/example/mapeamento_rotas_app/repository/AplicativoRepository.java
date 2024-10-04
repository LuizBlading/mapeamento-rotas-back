package com.example.mapeamento_rotas_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mapeamento_rotas_app.model.Aplicativo;


@Repository
public interface AplicativoRepository extends JpaRepository<Aplicativo,Integer>{
    
    Aplicativo findAplicativoById(Integer id);

    List<Aplicativo> findAplicativoByIdInstituicaoAndNomeAplicativo(Integer idInstituicao, String nomeAplicativo);
}
