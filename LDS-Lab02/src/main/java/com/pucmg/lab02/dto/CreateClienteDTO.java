package com.pucmg.lab02.dto;

import com.pucmg.lab02.Models.EntidadeEmpregadora;
import com.pucmg.lab02.Models.Usuario;
import java.util.List;

public class CreateClienteDTO {
    private Usuario usuario;
    private String cpf;
    private String rg;
    private List<EntidadeEmpregadora> entidadesEmpregadoras;

    public CreateClienteDTO() {
    }

    public CreateClienteDTO(Usuario usuario, String cpf, String rg, List<EntidadeEmpregadora> entidadesEmpregadoras) {
        this.usuario = usuario;
        this.cpf = cpf;
        this.rg = rg;
        this.entidadesEmpregadoras = entidadesEmpregadoras;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public List<EntidadeEmpregadora> getEntidadesEmpregadoras() {
        return entidadesEmpregadoras;
    }

    public void setEntidadesEmpregadoras(List<EntidadeEmpregadora> entidadesEmpregadoras) {
        this.entidadesEmpregadoras = entidadesEmpregadoras;
    }
}
