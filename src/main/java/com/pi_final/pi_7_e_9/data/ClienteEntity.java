package com.pi_final.pi_7_e_9.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.sql.Date;
import lombok.Data;

@Data
@Entity
@Table(name="Cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Size(min=4, message="Informe ao menos 4 caracteres para o campo nome")
    private String nome;
    
    @NotNull(message="Sata de nascimento obrigatória") 
    private Date data_nasc;
    
    @Size(min=4, message="Informe ao menos 4 caracteres para o campo endereço")
    private String endereco;
    
    @NotBlank(message="E-mail obrigatório")
    @Email(message="E-mail inválido")
    private String email;
}