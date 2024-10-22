package com.pucmg.lab02.dto;

import java.time.LocalDateTime;

import com.pucmg.lab02.Models.Contrato;
//TODO:
public class ContratoResponseDTO {
    private Long id;
    private int quantidadeDias;
    private Double valor;
    private String tipoRegistro;
    private boolean aprovacaoEmpresa;
    private String statusContrato;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
    private Double juros;
    private String tipoContrato;

    // Novos campos solicitados
    private String nomeCliente;
    private String cpfCliente;
    private String placaAutomovel;
    private String modeloAutomovel; // Aqui está o campo "modelo" do automóvel

    // Construtor que recebe o contrato e mapeia os dados
    public ContratoResponseDTO(Contrato contrato) {
        this.id = contrato.getId();
        this.quantidadeDias = contrato.getQuantidadeDias();
        this.valor = contrato.getValor();
        this.tipoRegistro = contrato.getTipoRegistro().toString();
        this.aprovacaoEmpresa = contrato.isAprovacaoEmpresa();
        this.statusContrato = contrato.getStatusContrato().toString();
        this.dataInicio = contrato.getDataInicio();
        this.dataTermino = contrato.getDataTermino();
        this.juros = contrato.getJuros();
        this.tipoContrato = contrato.getTipoContrato().toString();

        // Preenche os novos campos com dados do cliente e automóvel
        this.nomeCliente = contrato.getCliente().getNome();
        this.cpfCliente = contrato.getCliente().getCpf();
        this.placaAutomovel = contrato.getAutomovel().getPlaca();
        this.modeloAutomovel = contrato.getAutomovel().getModelo(); // Campo correto: modelo do automóvel
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidadeDias() {
        return this.quantidadeDias;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipoRegistro() {
        return this.tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public boolean isAprovacaoEmpresa() {
        return this.aprovacaoEmpresa;
    }

    public boolean getAprovacaoEmpresa() {
        return this.aprovacaoEmpresa;
    }

    public void setAprovacaoEmpresa(boolean aprovacaoEmpresa) {
        this.aprovacaoEmpresa = aprovacaoEmpresa;
    }

    public String getStatusContrato() {
        return this.statusContrato;
    }

    public void setStatusContrato(String statusContrato) {
        this.statusContrato = statusContrato;
    }

    public LocalDateTime getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataTermino() {
        return this.dataTermino;
    }

    public void setDataTermino(LocalDateTime dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Double getJuros() {
        return this.juros;
    }

    public void setJuros(Double juros) {
        this.juros = juros;
    }

    public String getTipoContrato() {
        return this.tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return this.cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getPlacaAutomovel() {
        return this.placaAutomovel;
    }

    public void setPlacaAutomovel(String placaAutomovel) {
        this.placaAutomovel = placaAutomovel;
    }

    public String getModeloAutomovel() {
        return this.modeloAutomovel;
    }

    public void setModeloAutomovel(String modeloAutomovel) {
        this.modeloAutomovel = modeloAutomovel;
    }

    
}
