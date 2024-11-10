package com.pi_final.pi_7_e_9.controller;

import com.pi_final.pi_7_e_9.data.FuncionarioEntity;
import com.pi_final.pi_7_e_9.service.FuncionarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController 

@RequestMapping("/funcionario")
public class FuncionarioController {
@Autowired
    
    FuncionarioService funcionarioService;
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllFuncionarios() {
        List<FuncionarioEntity> funcionarios = funcionarioService.listarTodosFuncionarios();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<FuncionarioEntity> getFuncionarioById(@PathVariable Integer id) {
        FuncionarioEntity funcionario = funcionarioService.getFuncionarioId(id);
        return new ResponseEntity<>(funcionario, HttpStatus.OK);
    }
    
    @PostMapping("/adicionar")
    public ResponseEntity<FuncionarioEntity> addFuncionario(@Valid @RequestBody FuncionarioEntity func) {
        var novoFuncionario = funcionarioService.criarFuncionario(func);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FuncionarioEntity> atualizarFuncionario(@PathVariable Integer id, @RequestBody FuncionarioEntity funcionario) {
        var funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionario);
        return new ResponseEntity<>(funcionarioAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarFuncionario(@PathVariable Integer id) {
        funcionarioService.deletarFuncionario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
