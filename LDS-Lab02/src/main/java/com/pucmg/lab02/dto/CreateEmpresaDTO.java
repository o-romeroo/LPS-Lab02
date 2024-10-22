package com.pucmg.lab02.dto;

import com.pucmg.lab02.Models.Usuario;
//TODO:
public class CreateEmpresaDTO {
    private Usuario usuario;
    private String cnpj;

    public CreateEmpresaDTO() {
    }

    public CreateEmpresaDTO(Usuario usuario, String cnpj) {
        this.usuario = usuario;
        this.cnpj = cnpj;
    }

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
