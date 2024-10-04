package com.pucmg.lab02.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pucmg.lab02.Models.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
}

