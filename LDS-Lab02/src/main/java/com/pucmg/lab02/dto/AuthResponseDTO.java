package com.pucmg.lab02.dto;

public class AuthResponseDTO {
    private boolean hasPermission;
    private String message;

    
    public AuthResponseDTO(boolean hasPermission, String message) {
        this.hasPermission = hasPermission;
        this.message = message;
    }

    public boolean isHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}