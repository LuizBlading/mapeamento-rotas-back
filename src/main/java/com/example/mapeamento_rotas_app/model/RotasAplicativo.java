package com.example.mapeamento_rotas_app.model;

import java.io.Serializable;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rotas_aplicativo", schema = "cadastro")
public class RotasAplicativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "id_aplicativo")
    private Integer idAplicativo;

    @Column(name = "id_rota")
    private Integer idRota;

    @Column(name = "id_instituicao")
    private Integer idInstituicao;

    @Column(name = "nome_aplicativo")
    private String nomeAplicativo;

    @Column(name = "nome_rota_api")
    private String nomeRotaApi;
}
