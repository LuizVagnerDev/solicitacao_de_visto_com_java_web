package com.pi_final.pi_7_e_9.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="Solicitacao")
public class SolicitacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    @NotNull(message="Registro do funcionário obrigatório")
    private FuncionarioEntity nomeFuncionario;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    @NotNull(message="Registro do cliente obrigatório")
    private ClienteEntity nomeCliente;
}
