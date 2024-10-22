package com.pucmg.lab02.Models;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



//TODO:
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contrato")
public class Contrato {

   public enum TipoRegistro {
    CLIENTE, EMPRESA;

    @JsonCreator
    public static TipoRegistro fromValue(String value) {
        return TipoRegistro.valueOf(value.toUpperCase());
    }
}

    public enum StatusContrato{
        ATIVO, FINALIZADO, CANCELADO, PENDENTE
    }

    public enum TipoContrato {
        DEBITO, EMPRESTIMO;
    
        @JsonCreator
        public static TipoContrato fromValue(String value) {
            return TipoContrato.valueOf(value.toUpperCase());
        }
    }
    
    public boolean isEmprestimo(){
        return this.tipoContrato == TipoContrato.EMPRESTIMO;
    }

    public boolean isDebito(){
        return this.tipoContrato == TipoContrato.DEBITO;
    }

    public boolean isCliente(){
        return this.tipoRegistro == TipoRegistro.CLIENTE;
    }

    public boolean isEmpresa(){
        return this.tipoRegistro == TipoRegistro.EMPRESA;
    }

    public boolean isAtivo(){
        return this.statusContrato == StatusContrato.ATIVO;
    }

    public boolean isFinalizado(){
        return this.statusContrato == StatusContrato.FINALIZADO;
    }

    public boolean isCancelado(){
        return this.statusContrato == StatusContrato.CANCELADO;
    }

    public boolean isPendente(){
        return this.statusContrato == StatusContrato.PENDENTE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "automovel_id")
    @JsonIgnore
    private Automovel automovel;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonIgnore
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "banco_id")
    @JsonIgnore
    private Banco banco;


    @Column
    private int quantidadeDias;

    @Column
    private Double valor;

    @Column
    private TipoRegistro tipoRegistro;

    @Column
    private boolean aprovacaoEmpresa;

    @Column
    private StatusContrato statusContrato;

    @Column
    private LocalDateTime dataInicio;

    @Column
    private LocalDateTime dataTermino;

    @Column
    private Double juros;

    @Column
    private TipoContrato tipoContrato;


    public Contrato(Optional<Automovel> automovel, Cliente cliente, Empresa empresa, int quantidadeDias, TipoRegistro tipoRegistro, TipoContrato tipoContrato) {
        this.automovel = automovel.orElse(null);
        this.cliente = cliente;
        this.empresa = empresa;
        this.quantidadeDias = quantidadeDias;
        this.tipoRegistro = tipoRegistro;
        this.tipoContrato = tipoContrato;
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Automovel getAutomovel() {
        return this.automovel;
    }

    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Banco getBanco() {
        return this.banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
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

    public TipoRegistro getTipoRegistro() {
        return this.tipoRegistro;
    }

    public void setTipoRegistro(TipoRegistro tipoRegistro) {
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

    public StatusContrato getStatusContrato() {
        return this.statusContrato;
    }

    public void setStatusContrato(StatusContrato statusContrato) {
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

}

