package com.pi_final.pi_7_e_9.controller;

import com.pi_final.pi_7_e_9.data.ClienteEntity;
import com.pi_final.pi_7_e_9.service.ClienteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 

@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    
    ClienteService clienteService;
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllClientes() {
        List<ClienteEntity> funcionarios = clienteService.listarTodosClientes();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<ClienteEntity> getClienteById(@PathVariable Integer id) {
        ClienteEntity cliente = clienteService.getClienteId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
    
    @PostMapping("/adicionar")
    public ResponseEntity<ClienteEntity> addCliente(@Valid @RequestBody ClienteEntity cli) {
        var novoCliente = clienteService.criarCliente(cli);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteEntity> atualizarCliente(@PathVariable Integer id, @RequestBody ClienteEntity cliente) {
        var clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarCliente(@PathVariable Integer id) {
        clienteService.deletarCliente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
