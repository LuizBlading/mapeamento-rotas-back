package com.example.mapeamento_rotas_app.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rotas", schema = "cadastro")
public class Rotas implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "nome_rota_api", nullable = false)
    private String nomeRotaApi;

    @Column(name = "base_rota", nullable = false)
    private String baseRota;

}
