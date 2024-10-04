package com.pucmg.lab02.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pucmg.lab02.Models.Cliente;
import com.pucmg.lab02.Models.EntidadeEmpregadora;
import com.pucmg.lab02.Repositories.ClienteRepository;
import com.pucmg.lab02.Repositories.EntidadeEmpregadoraRepository;

import java.util.*;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EntidadeEmpregadoraRepository entidadeEmpregadoraRepository;

    @Transactional
    public Cliente create(Cliente obj) {
        obj.setId(null);
        try {
            obj = this.clienteRepository.save(obj);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar cliente");
        }
        return obj;
    }

    @Transactional
    public void deletarCliente(String cpf) {
        clienteRepository.deleteByCpf(cpf);
    }

    @Transactional
    public Cliente consultarCliente(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Transactional
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente atualizarAutomovelCliente(Cliente obj) {
        Cliente newObj = clienteRepository.findByCpf(obj.getCpf());
        newObj.setAutomoveis(obj.getAutomoveis());
        return this.clienteRepository.save(newObj);
    }

    @Transactional
    public EntidadeEmpregadora createEmpregadora (EntidadeEmpregadora obj) {
        obj.setId(null);
        try {
            obj = this.entidadeEmpregadoraRepository.save(obj);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar entidade empregadora");
        }
        return obj;
    }

    @Transactional
    public Cliente findById (Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
    

}
