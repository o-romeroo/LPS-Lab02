package com.pucmg.lab02.dto;

import com.pucmg.lab02.Models.Automovel;

public class EditAutomovelDTO {
    Automovel automovel;
   
    public EditAutomovelDTO(Automovel automovel) {
        this.automovel = automovel;
    }

    public Automovel getAutomovel() {
        return automovel;
    }


    

    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }
}