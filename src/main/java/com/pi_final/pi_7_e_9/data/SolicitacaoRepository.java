package com.pi_final.pi_7_e_9.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoRepository extends JpaRepository<SolicitacaoEntity, Integer>{
    
}
