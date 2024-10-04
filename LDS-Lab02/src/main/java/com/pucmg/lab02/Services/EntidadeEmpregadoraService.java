package com.pucmg.lab02.Services;

import org.springframework.stereotype.Service;

import com.pucmg.lab02.Repositories.EntidadeEmpregadoraRepository;
import com.pucmg.lab02.Models.EntidadeEmpregadora;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EntidadeEmpregadoraService {

    @Autowired
    private EntidadeEmpregadoraRepository entidadeEmpregadoraRepository;

    public EntidadeEmpregadora save(EntidadeEmpregadora entidadeEmpregadora) {
        return entidadeEmpregadoraRepository.save(entidadeEmpregadora);
    }
}
