package com.pi_final.pi_7_e_9.service;

import com.pi_final.pi_7_e_9.data.ClienteEntity;
import com.pi_final.pi_7_e_9.data.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;

    public ClienteEntity criarCliente(ClienteEntity c) {

        c.setId(null);

        clienteRepository.save(c);

        return c;
    }

    public ClienteEntity atualizarCliente(Integer cliId, ClienteEntity clienteRequest){
        ClienteEntity c = getClienteId(cliId);
        c.setNome(clienteRequest.getNome());
        c.setData_nasc(clienteRequest.getData_nasc());
        c.setEndereco(clienteRequest.getEndereco());
        c.setEmail(clienteRequest.getEmail());

        clienteRepository.save(c);
        return c;
    }

    public ClienteEntity getClienteId(Integer cliId) {

        return clienteRepository.findById(cliId).orElse(null);
    }

    public List<ClienteEntity> listarTodosClientes() {

        return clienteRepository.findAll();
    }

    public void deletarCliente(Integer cliId) {

        ClienteEntity c = getClienteId(cliId);

        clienteRepository.deleteById(c.getId());
    }      

   
}
