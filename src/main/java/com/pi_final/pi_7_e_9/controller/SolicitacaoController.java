/*package com.pi_final.pi_7_e_9.controller;

import com.pi_final.pi_7_e_9.data.SolicitacaoEntity;
import com.pi_final.pi_7_e_9.service.SolicitacaoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController 
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    @Autowired
    
    SolicitacaoService solicitacaoService;
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllSolicitacoes() {
        List<SolicitacaoEntity> solicitacoes = solicitacaoService.listarTodasSolicitacoes();
        return new ResponseEntity<>(solicitacoes, HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<SolicitacaoEntity> getSolicitacaoById(@PathVariable Integer id) {
        SolicitacaoEntity solicitacao = solicitacaoService.getSolicitacaoId(id);
        return new ResponseEntity<>(solicitacao, HttpStatus.OK);
    }
    
    @PostMapping("/adicionar")
    public ResponseEntity<SolicitacaoEntity> addSolicitacao(@Valid @RequestBody SolicitacaoEntity sol) {
        var novaSolicitacao = solicitacaoService.criarSolicitacao(sol);
        return new ResponseEntity<>(novaSolicitacao, HttpStatus.CREATED);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<SolicitacaoEntity> atualizarSolicitacao(@PathVariable Integer id, @RequestBody SolicitacaoEntity solicitacao) {
        var solicitacaoAtualizada = solicitacaoService.atualizarSolicitacao(id, solicitacao);
        return new ResponseEntity<>(solicitacaoAtualizada, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarSolicitacao(@PathVariable Integer id) {
        solicitacaoService.deletarSolicitacao(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }    
}
*/