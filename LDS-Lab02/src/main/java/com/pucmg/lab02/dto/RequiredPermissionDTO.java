package com.pucmg.lab02.dto;

//TODO:
public class RequiredPermissionDTO {
    private Long id;
    private String role;

    public RequiredPermissionDTO(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
