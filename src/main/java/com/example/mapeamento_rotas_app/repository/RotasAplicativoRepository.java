package com.example.mapeamento_rotas_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mapeamento_rotas_app.model.RotasAplicativo;

@Repository
public interface RotasAplicativoRepository extends JpaRepository<RotasAplicativo, Integer>{
    
    @Query(value = "select ra from RotasAplicativo ra where ra.idRota = :idRota ")
    List<RotasAplicativo> findRotasEmUsoByIdRota(@Param("idRota") Integer idRota);

    @Query(value = "select ra from RotasAplicativo ra where ra.idAplicativo = :idAplicativo ")
    List<RotasAplicativo> findRotasEmUsoByIdAplicativo(@Param("idAplicativo") Integer idAplicativo);
}
