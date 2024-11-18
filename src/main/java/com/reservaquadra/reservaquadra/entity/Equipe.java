package com.reservaquadra.reservaquadra.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_equipe")
public class Equipe implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "aluguel_id", nullable = false)
    private Aluguel aluguels;

    @OneToMany(mappedBy = "equipe")
    private final List<Jogador> jogadores = new ArrayList<>();

    public Equipe() {
    }

    public Equipe(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Aluguel getAluguels() {
        return aluguels;
    }

    public void setAluguels(Aluguel aluguels) {
        this.aluguels = aluguels;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipe equipe = (Equipe) o;
        return Objects.equals(id, equipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
