package com.pucmg.lab02.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pucmg.lab02.Models.Automovel;
import com.pucmg.lab02.Models.Contrato;
import com.pucmg.lab02.Models.Usuario;


@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
    Automovel findByPlaca(String placa);
    List<Automovel> findByDono(Usuario dono);
    void deleteById(Long id);

    List<Automovel> findByContratoIsNullOrContratoStatusContratoNot(Contrato.StatusContrato status);

} 
