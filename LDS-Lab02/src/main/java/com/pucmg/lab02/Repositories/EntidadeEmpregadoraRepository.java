package com.pucmg.lab02.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pucmg.lab02.Models.EntidadeEmpregadora;

@Repository
public interface EntidadeEmpregadoraRepository extends JpaRepository<EntidadeEmpregadora, Long> {
    

}
