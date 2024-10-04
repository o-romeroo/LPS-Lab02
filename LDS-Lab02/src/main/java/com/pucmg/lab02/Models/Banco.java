package com.pucmg.lab02.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "banco")
public class Banco extends Usuario {

    @Column
    private String cnpj;

    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Contrato> contratos;

    public Banco(String login, String senha, String nome, String endereco, String cnpj) {
        super(login, senha, nome, endereco);
        this.cnpj = cnpj;
    }
    public Banco(String cnpj) {
        super(); // Chama o construtor da classe pai (Usuario)
        this.cnpj = cnpj;
    }

}

