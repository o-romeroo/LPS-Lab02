package com.pucmg.lab02.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pucmg.lab02.Models.Automovel;
import com.pucmg.lab02.Models.Contrato;
import com.pucmg.lab02.Models.Usuario;
import com.pucmg.lab02.Repositories.AutomovelRepository;

@Service
public class AutomovelService {
    
    @Autowired
    AutomovelRepository automovelRepository;
    
    @Transactional
    public void salvarAutomovel(Automovel automovel) {
        automovelRepository.save(automovel);
    }

    @Transactional
    public Automovel criarAutomovel(int ano, String marca, String placa, Double precoDiaria, Usuario dono) {
        Automovel automovel = new Automovel();
        automovel.setAno(ano);
        automovel.setMarca(marca);
        automovel.setPlaca(placa);
        automovel.setPrecoDiaria(precoDiaria);
        automovel.setDono(dono);
        return automovelRepository.save(automovel);
    }   



    @Transactional
    public Automovel atualizarAutomovel(Automovel automovel) {
    // Busca o automóvel existente no banco de dados
    Automovel automovelAtual = automovelRepository.findById(automovel.getId())
            .orElseThrow(() -> new RuntimeException("Automóvel não encontrado"));

    // Atualiza os campos apenas se eles foram fornecidos na requisição (não são nulos)
    if (automovel.getAno() != 0) {
        automovelAtual.setAno(automovel.getAno());
    }
    if (automovel.getModelo() != null) {
        automovelAtual.setModelo(automovel.getModelo());
    }
    if (automovel.getMarca() != null) {
        automovelAtual.setMarca(automovel.getMarca());
    }
    if (automovel.getPlaca() != null) {
        automovelAtual.setPlaca(automovel.getPlaca());
    }
    if (automovel.getPrecoDiaria() != 0.0) {
        automovelAtual.setPrecoDiaria(automovel.getPrecoDiaria());
    }

    // Salva as atualizações no banco de dados
    return automovelRepository.save(automovelAtual);
}


    @Transactional
    public void deletarAutomovel(Long id) {
        automovelRepository.deleteById(id);
    }

    @Transactional
    public Optional<Automovel> consultarAutomovelById(Long id) {
        return automovelRepository.findById(id);
    }

    @Transactional
    public List<Automovel> listarAutomoveis() {
        return automovelRepository.findAll();
    }

    @Transactional
    public List<Automovel> listarAutomoveisByDono(Usuario dono) {
        return automovelRepository.findByDono(dono);
    }

    @Transactional
    public List<Automovel> listarAutomoveisDisponiveis() {
        // Assumindo que um Automovel está disponível se não tiver um contrato ativo
        return automovelRepository.findByContratoIsNullOrContratoStatusContratoNot(Contrato.StatusContrato.ATIVO);
    }
}
