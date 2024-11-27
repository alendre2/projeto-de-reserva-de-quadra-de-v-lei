package com.reservaquadra.reservaquadra.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_partida")
public class Partida implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pontoEquipeUm;

    @Column(nullable = false)
    private Long pontoEquipeDois;

    @Column(nullable = false)
    private Equipe equipe1;

    @Column(nullable = false)
    private Equipe equipe2;

    @OneToMany(mappedBy = "partida")
    private final List<Equipe> equipes = new ArrayList<>();

    @ManyToOne
    private Aluguel aluguel;

    public Partida() {
    }

    public Partida(Equipe equipe1, Equipe equipe2, Aluguel aluguel) {
        this.pontoEquipeUm = 0L;
        this.pontoEquipeDois = 0L;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.aluguel = aluguel;
    }

    public Long getId() {
        return id;
    }

    public Long getPontoEquipeUm() {
        return pontoEquipeUm;
    }

    public void setPontoEquipeUm(Long pontoEquipeUm) {
        this.pontoEquipeUm = pontoEquipeUm;
    }

    public Long getPontoEquipeDois() {
        return pontoEquipeDois;
    }

    public void setPontoEquipeDois(Long pontoEquipeDois) {
        this.pontoEquipeDois = pontoEquipeDois;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }
}
