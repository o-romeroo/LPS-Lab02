package com.pucmg.lab02.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;


//TODO:
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "empresa")
public class Empresa extends Usuario {
    @Column
    private String cnpj;
   
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Impede a serialização infinita quando os contratos são serializados
    private List<Contrato> contratos;

    public Empresa(String cnpj) {
        this.cnpj = cnpj;
    }
    

    public Empresa(String login, String senha, String nome, String endereco, String cnpj) {
        super(login, senha, nome, endereco);
        this.cnpj = cnpj;
    }
}
