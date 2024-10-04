package com.pucmg.lab02.Models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "automovel")
public class Automovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int ano;

    @Column 
    private String marca;

    @Column
    private String modelo;

    @Column
    private String placa;
    
    @Column
    private double precoDiaria;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")  // Relacionamento correto com Usuario
    private Usuario dono;  // Este é o "dono" do automóvel

    @OneToOne(mappedBy = "automovel")
    private Contrato contrato;

    
}
