package com.pucmg.lab02.dto;

import com.pucmg.lab02.Models.Automovel;
//TODO:
public class CreateAutomovelDTO {
    Automovel automovel;
    Long empresaId;

    public CreateAutomovelDTO(Automovel automovel, Long empresaId) {
        this.automovel = automovel;
        this.empresaId = empresaId;
    }

    public Automovel getAutomovel() {
        return automovel;
    }


    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }
}