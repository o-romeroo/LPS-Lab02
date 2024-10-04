package com.pucmg.lab02.dto;

import com.pucmg.lab02.Models.Contrato.TipoContrato;
import com.pucmg.lab02.Models.Contrato.TipoRegistro;

public class ContratoDataDTO {
    private Long automovelId;
    private Long clienteId;
    private int quantidadeDias;
    private TipoRegistro tipoRegistro;
    private TipoContrato tipoContrato;
    private Long bancoId;

    public ContratoDataDTO() {
    }

    public ContratoDataDTO(Long automovelId, Long clienteId, int quantidadeDias, TipoRegistro tipoRegistro, TipoContrato tipoContrato) {
        this.automovelId = automovelId;
        this.clienteId = clienteId;
        this.quantidadeDias = quantidadeDias;
        this.tipoRegistro = tipoRegistro;
        this.tipoContrato = tipoContrato;
    }

    

    public ContratoDataDTO(Long automovelId, Long clienteId, int quantidadeDias, TipoRegistro tipoRegistro, TipoContrato tipoContrato, Long bancoId) {
        this.automovelId = automovelId;
        this.clienteId = clienteId;
        this.quantidadeDias = quantidadeDias;
        this.tipoRegistro = tipoRegistro;
        this.tipoContrato = tipoContrato;
        this.bancoId = bancoId;
    }

    public Long getAutomovelId() {
        return automovelId;
    }
    public void setAutomovelId(Long automovelId) {
        this.automovelId = automovelId;
    }
    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    public int getQuantidadeDias() {
        return quantidadeDias;
    }
    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }
    public TipoRegistro getTipoRegistro() {
        return tipoRegistro;
    }
    public void setTipoRegistro(TipoRegistro tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }
    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }
    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    public Long getBancoId() {
        return bancoId;
    }
    public void setBancoId(Long bancoId) {
        this.bancoId = bancoId;
    }
}
