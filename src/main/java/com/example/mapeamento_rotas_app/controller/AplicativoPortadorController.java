package com.example.mapeamento_rotas_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapeamento_rotas_app.model.Aplicativo;
import com.example.mapeamento_rotas_app.model.Rotas;
import com.example.mapeamento_rotas_app.model.RotasAplicativo;
import com.example.mapeamento_rotas_app.model.Request.RotasAplicativoRequest;
import com.example.mapeamento_rotas_app.model.Response.RotasAplicativoResponse;
import com.example.mapeamento_rotas_app.repository.AplicativoRepository;
import com.example.mapeamento_rotas_app.service.AplicativoService;
import com.example.mapeamento_rotas_app.service.RotasService;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
@RequestMapping("/api/aplicativo")
public class AplicativoPortadorController {
    
    @Autowired
    private AplicativoService aplicativoService;

    @Autowired
    private RotasService rotasService;

    @PostMapping("/adicionar-aplicativo")
    public ResponseEntity<HashMap<String,String>> addAplicativo(@RequestBody Aplicativo aplicativo) throws Exception {
        return new ResponseEntity<>(aplicativoService.cadastrarAplicativo(aplicativo), HttpStatus.OK);
    }

    @GetMapping("/buscar-aplicativos")
    public ResponseEntity<List<Aplicativo>> buscarTodosAplicativos() throws Exception{
        return new ResponseEntity<List<Aplicativo>>(this.aplicativoService.buscarTodosAplicativos(), HttpStatus.OK);
    }

    @GetMapping("/buscar-aplicativo/{id}")
    public ResponseEntity<Aplicativo> buscarAplicativoById(@PathVariable("id") Integer id) throws Exception{
        return new ResponseEntity<Aplicativo>(this.aplicativoService.buscarAplicativoById(id), HttpStatus.OK);
    }
    
    @PostMapping("/rotas/adicionar-rota")
    public ResponseEntity<HashMap<String,String>> cadastrarRota(@RequestBody Rotas rotas) throws Exception{
        return new ResponseEntity<>(rotasService.cadastrarRota(rotas),HttpStatus.OK);
    }

    @GetMapping("/rotas/buscar-rotas-cadastradas")
    public ResponseEntity<List<Rotas>> buscarRotasCadastradas() throws Exception {
        return new ResponseEntity<List<Rotas>>(this.rotasService.buscarRotasCadastradas(), HttpStatus.OK);
    }

    @PostMapping("/rotas/vincular-rota-aplicativo")
    public ResponseEntity<String> vincularRotaAplicativo(@RequestBody RotasAplicativoRequest rotasAplicativo) throws Exception {
        return new ResponseEntity<String>(this.rotasService.vincularAplicativoRota(rotasAplicativo), HttpStatus.CREATED);
    }

    @GetMapping("/rotas/buscar-rotas-aplicativo/{idAplicativo}")
    public ResponseEntity<List<RotasAplicativo>> buscarRotasAplicativo(@PathVariable("idAplicativo") Integer idAplicativo) {
        return new ResponseEntity<List<RotasAplicativo>>(this.rotasService.buscarRotasAplicativo(idAplicativo),HttpStatus.OK);        
    }

    @GetMapping("/rotas/rotas-em-uso/{idRota}")
    public ResponseEntity<List<RotasAplicativo>> buscarRotasEmUso(@PathVariable("idRota") Integer idRota) throws Exception {
        return new ResponseEntity<List<RotasAplicativo>>(this.rotasService.buscarRotasEmUso(idRota), HttpStatus.OK);
    }

    @GetMapping("/rotas/aplicativos-rotas")
    public ResponseEntity<List<RotasAplicativoResponse>> buscarTodosAplicativosComRotas() throws Exception {
        return new ResponseEntity<List<RotasAplicativoResponse>>(this.rotasService.buscarAplicativosComRotas(), HttpStatus.OK);
    }
}
