package com.pi_final.pi_7_e_9.service;

import com.pi_final.pi_7_e_9.data.FuncionarioEntity;
import com.pi_final.pi_7_e_9.data.FuncionarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;
    
    public FuncionarioEntity criarFuncionario(FuncionarioEntity f) {
        f.setId(null);
        switch(f.getCargo()){
            case "analista_jr":
                f.setCargo("Analista de processos Júnior");
                break;
            
            case "analista_pl":
                f.setCargo("Analista de processos Pleno");
                break;
            
            case "gerente":
                f.setCargo("Gerente");
                break;
            
            case "supervisor":
                f.setCargo("Supervisor");
                break;
                
            default:
                f.setCargo("cargo não informado");
        }
        funcionarioRepository.save(f);
        return f;
    }
    
    public FuncionarioEntity atualizarFuncionario(Integer funcId, FuncionarioEntity funcionarioRequest) {
        FuncionarioEntity f = getFuncionarioId(funcId);
        f.setNome(funcionarioRequest.getNome());
        f.setCargo(funcionarioRequest.getCargo());
        funcionarioRepository.save(f);
        return f;
    } 

    public FuncionarioEntity getFuncionarioId(Integer funcId) { 
        return funcionarioRepository.findById(funcId).orElse(null);
    } 

    public List<FuncionarioEntity> listarTodosFuncionarios() { 
        return funcionarioRepository.findAll();
    }
    
    public void deletarFuncionario(Integer funcId) { 
        FuncionarioEntity func = getFuncionarioId(funcId); 
        funcionarioRepository.deleteById(func.getId());
    }    
}
