package com.pi_final.pi_7_e_9.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="Funcionario")
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min=4, message="Informe ao menos 4 caracteres para o campo nome")
    private String nome;
    private String cargo;
    
}
