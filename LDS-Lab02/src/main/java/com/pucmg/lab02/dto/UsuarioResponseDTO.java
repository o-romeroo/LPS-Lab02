package com.pucmg.lab02.dto;
//TODO:
public class UsuarioResponseDTO {
    private Long idlogin;
    private String userType; // Novo campo para armazenar o tipo de usuário

    // Construtor original (mantém compatibilidade com código existente)
    public UsuarioResponseDTO(Long idlogin) {
        this.idlogin = idlogin;
    }

    // Construtor novo com tipo de usuário
    public UsuarioResponseDTO(Long idlogin, String userType) {
        this.idlogin = idlogin;
        this.userType = userType;
    }

    // Getters e Setters
    public Long getIdLogin() {
        return idlogin;
    }

    public void setIdLogin(Long idlogin) {
        this.idlogin = idlogin;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
