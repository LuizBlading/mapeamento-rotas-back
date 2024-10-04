package com.example.mapeamento_rotas_app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapeamento_rotas_app.model.Aplicativo;
import com.example.mapeamento_rotas_app.repository.AplicativoRepository;

@Service
public class AplicativoService {

    @Autowired
    private AplicativoRepository aplicativoRepository;

    private static final Logger logger = LogManager.getLogger("AplicationService");

    public AplicativoService(AplicativoRepository aplicativoRepository) {
        this.aplicativoRepository = aplicativoRepository;
    }

    public Aplicativo buscarAplicativoById(Integer id) throws Exception{

        if(id == null) {
            throw new Exception("ID não pode ser nulo, favor informar um ID");
        }
        
        Aplicativo aplicativo = this.aplicativoRepository.findAplicativoById(id);

        if(aplicativo == null) {
            throw new Exception("Nenhum aplicativo foi encontrado com o ID " + id);
        }

        return aplicativo;
    }

    public List<Aplicativo> buscarTodosAplicativos() throws Exception{
        List<Aplicativo> aplicativos = new ArrayList<>();
        try {
            aplicativos = this.aplicativoRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Não foi possivel buscar Aplicativos");
        }
        return aplicativos;
    } 

    public HashMap<String, String> cadastrarAplicativo(Aplicativo aplicativo) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        try {
            List<Aplicativo> app = 
                aplicativoRepository.findAplicativoByIdInstituicaoAndNomeAplicativo(aplicativo.getIdInstituicao(), aplicativo.getNomeAplicativo());
            
            if(app != null && app.size() > 0) {           
                throw new Exception("Não é permitido cadastrar um aplicativo da mesma Instituição com o mesmo nome");
            }

            aplicativoRepository.save(aplicativo);
            map.put("msg", "Aplicativo adicionado com sucesso!");
        } catch (Exception e) {
            logger.error(e.getMessage());
            map.put("msg",e.getMessage());
        }

        return map;
    }
}
