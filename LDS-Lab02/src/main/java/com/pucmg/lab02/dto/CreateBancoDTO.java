package com.pucmg.lab02.dto;

import com.pucmg.lab02.Models.Usuario;

//TODO:
public class CreateBancoDTO {


    private String cnpj;

    private Usuario usuario;

    public CreateBancoDTO() {
    }

    public CreateBancoDTO(Usuario usuario, String cnpj) {
        this.usuario = usuario;
        this.cnpj = cnpj;
    }

    // Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
