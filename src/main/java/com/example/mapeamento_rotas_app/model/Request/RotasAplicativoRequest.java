package com.example.mapeamento_rotas_app.model.Request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotasAplicativoRequest implements Serializable{

    private static final long serialVersionUID = 1L;

    public Integer idAplicativo;
    public Integer idRota;
}
