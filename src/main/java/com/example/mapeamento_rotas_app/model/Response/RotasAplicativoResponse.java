package com.example.mapeamento_rotas_app.model.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.example.mapeamento_rotas_app.model.RotasAplicativo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotasAplicativoResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    public Integer idAplicativo;
    public String nomeAplicativo;
    public List<RotasAplicativo> rotas = new ArrayList<>();
}
