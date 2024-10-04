package com.example.mapeamento_rotas_app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapeamento_rotas_app.model.Aplicativo;
import com.example.mapeamento_rotas_app.model.Rotas;
import com.example.mapeamento_rotas_app.model.RotasAplicativo;
import com.example.mapeamento_rotas_app.model.Request.RotasAplicativoRequest;
import com.example.mapeamento_rotas_app.model.Response.RotasAplicativoResponse;
import com.example.mapeamento_rotas_app.repository.RotasAplicativoRepository;
import com.example.mapeamento_rotas_app.repository.RotasRepository;

@Service
public class RotasService {

    @Autowired
    private RotasRepository rotasRepository;

    @Autowired
    private RotasAplicativoRepository rotasAplicativoRepository;

    @Autowired
    private AplicativoService aplicativoService;

    private static final Logger logger = LogManager.getLogger(RotasService.class);

    public HashMap<String,String> cadastrarRota(Rotas rota) throws Exception{
        HashMap<String, String> map = new HashMap();
        try {
            rotasRepository.save(rota);
            map.put("msg", "Rota cadastrada com sucesso!");
        } catch(Exception e) {
            map.put("msg","Não foi possível cadastrar rota");
            logger.error(e.getMessage());
            throw new Exception("Não foi possível cadastrar rota");
        }
        return map;
    }

    public List<Rotas> buscarRotasCadastradas() throws Exception {
        List<Rotas> rotas = new ArrayList<>();
        try {
            rotas = rotasRepository.findAll();
            
            if(rotas.isEmpty()) {
                throw new Exception("Não foi encontrada nenhuma rota no sistema");
            } 
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return rotas;
    }

    public String vincularAplicativoRota(RotasAplicativoRequest request) throws Exception{

        try {

            Aplicativo aplicativo = aplicativoService.buscarAplicativoById(request.idAplicativo);
            Rotas rotas = rotasRepository.findRotaById(request.idRota);

            RotasAplicativo rotasAplicativo = new RotasAplicativo();
            rotasAplicativo.setIdAplicativo(aplicativo.getId());
            rotasAplicativo.setIdRota(rotas.getId());
            rotasAplicativo.setIdInstituicao(aplicativo.getIdInstituicao());
            rotasAplicativo.setNomeAplicativo(aplicativo.getNomeAplicativo());
            rotasAplicativo.setNomeRotaApi(rotas.getNomeRotaApi());
            rotasAplicativoRepository.save(rotasAplicativo);

            return "Vinculo feito com sucesso!";
        } catch(Exception e) {
            logger.error(e.getMessage());
            throw new Exception("Não foi possível vincular rota ao aplicativo.");
        }
    }

    public List<RotasAplicativo> buscarRotasEmUso(Integer idRota) throws Exception {
        
        List<RotasAplicativo> rotasEmUso = new ArrayList<>();
        
        try {
            if(idRota == null) {
                throw new Exception("ID Rota nulo, informe um valor");
            } 
            rotasEmUso = rotasAplicativoRepository.findRotasEmUsoByIdRota(idRota);
        } catch(Exception e) {
            logger.error(e.getMessage());
            throw new Exception("Não foi possível buscar rotas. " + e.getMessage());
        }

        return rotasEmUso;
    }

    public List<RotasAplicativo> buscarRotasAplicativo(Integer idAplicativo) {
        return this.rotasAplicativoRepository.findRotasEmUsoByIdAplicativo(idAplicativo);
    }

    public List<RotasAplicativoResponse> buscarAplicativosComRotas() throws Exception {
        List<RotasAplicativoResponse> response = new ArrayList<>();
        List<Aplicativo> aplicativos = this.aplicativoService.buscarTodosAplicativos();

        for (Aplicativo aplicativo : aplicativos) {
            List<RotasAplicativo> rotas = this.rotasAplicativoRepository.findRotasEmUsoByIdAplicativo(aplicativo.getId());

            RotasAplicativoResponse rotasAplicativo = new RotasAplicativoResponse();
            rotasAplicativo.setIdAplicativo(aplicativo.getId());
            rotasAplicativo.setNomeAplicativo(aplicativo.getNomeAplicativo());

            for (RotasAplicativo rota : rotas) {
                rotasAplicativo.rotas.add(rota);
            }

            response.add(rotasAplicativo);
        }
        
        return response;
    }
}
