package com.pucmg.lab02.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pucmg.lab02.Models.Cliente;
import com.pucmg.lab02.Models.Contrato;
import com.pucmg.lab02.Models.Contrato.StatusContrato;
import com.pucmg.lab02.Models.Empresa;


@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    List<Contrato> findAllByStatusContrato(StatusContrato statusContrato);

    List<Contrato> findByEmpresaAndStatusContrato(Empresa empresa, StatusContrato statusContrato);

    List<Contrato> findByClienteAndStatusContrato(Cliente cliente, StatusContrato statusContrato);

}
