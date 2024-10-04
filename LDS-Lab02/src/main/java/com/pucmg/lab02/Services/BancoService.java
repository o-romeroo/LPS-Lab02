package com.pucmg.lab02.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab02.Models.Banco;
import com.pucmg.lab02.Repositories.BancoRepository;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    public Banco findById(Long id) {
        return bancoRepository.findById(id).orElse(null);
    }

    public Banco save(Banco banco) {
        return bancoRepository.save(banco);
    }

    public List<Banco> findAll() {
        return bancoRepository.findAll();
    }

    
}
