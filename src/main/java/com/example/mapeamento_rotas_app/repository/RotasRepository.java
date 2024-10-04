package com.example.mapeamento_rotas_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mapeamento_rotas_app.model.Rotas;

@Repository
public interface RotasRepository extends JpaRepository<Rotas,Integer>{
    Rotas findRotaById(Integer idRota);
}
