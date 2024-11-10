package com.pi_final.pi_7_e_9.service;

import com.pi_final.pi_7_e_9.data.SolicitacaoEntity;
import com.pi_final.pi_7_e_9.data.SolicitacaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoService {
    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    public SolicitacaoEntity criarSolicitacao(SolicitacaoEntity s) {
        s.setId(null);
        solicitacaoRepository.save(s);
        return s;
    }
    
    public SolicitacaoEntity atualizarSolicitacao(Integer solicId, SolicitacaoEntity solicitacaoRequest) {
        SolicitacaoEntity s = getSolicitacaoId(solicId);
        s.setNomeFuncionario(solicitacaoRequest.getNomeFuncionario());
        s.setNomeCliente(solicitacaoRequest.getNomeCliente());
        solicitacaoRepository.save(s);
        return s;
    } 

    public SolicitacaoEntity getSolicitacaoId(Integer solicId) { 
        return solicitacaoRepository.findById(solicId).orElse(null);
    } 

    public List<SolicitacaoEntity> listarTodasSolicitacoes() { 
        return solicitacaoRepository.findAll();
    }
    
    public void deletarSolicitacao(Integer solicId) { 
        SolicitacaoEntity solic = getSolicitacaoId(solicId); 
        solicitacaoRepository.deleteById(solic.getId());
    }        
    
}
