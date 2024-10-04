package com.pucmg.lab02.Services;

import com.pucmg.lab02.Models.Empresa;
import com.pucmg.lab02.Repositories.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa findById(Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.orElse(null);
    }

    public Empresa findByCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        empresaRepository.deleteById(id);
    }

    @Transactional
    public Empresa update(Empresa empresa) {
        if (empresa.getId() == null || !empresaRepository.existsById(empresa.getId())) {
            throw new RuntimeException("Empresa não encontrada para atualização");
        }
        return empresaRepository.save(empresa);
    }
}
