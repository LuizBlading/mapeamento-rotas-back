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
@Table(name = "aplicativo", schema = "cadastro")
public class Aplicativo implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Verificar como usar melhor essa anotação
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "nome_aplicativo", nullable = false)
    private String nomeAplicativo;

    @Column(name = "id_instituicao", nullable = false)
    private Integer idInstituicao;

}
