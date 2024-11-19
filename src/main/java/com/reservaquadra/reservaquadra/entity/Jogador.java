package com.reservaquadra.reservaquadra.entity;

import com.reservaquadra.reservaquadra.enums.StatusJogador;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_jogador")
public class Jogador implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String contato;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @Enumerated(EnumType.STRING)
    private StatusJogador statusJogador;

    public Jogador() {
    }

    public Jogador(String nome, String contato, Equipe equipe, StatusJogador statusJogador) {
        this.nome = nome;
        this.contato = contato;
        this.equipe = equipe;
        this.statusJogador = statusJogador;
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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public StatusJogador getStatusJogador() {
        return statusJogador;
    }

    public void setStatusJogador(StatusJogador statusJogador) {
        this.statusJogador = statusJogador;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return Objects.equals(id, jogador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
