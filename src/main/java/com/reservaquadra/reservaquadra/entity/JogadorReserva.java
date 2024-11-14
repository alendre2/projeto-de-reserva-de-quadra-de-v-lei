package com.reservaquadra.reservaquadra.entity;

import jakarta.persistence.*;

@Entity
public class JogadorReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Relacionamento com a entidade Jogador
    private Jogador jogador;

    @ManyToOne // Relacionamento com a entidade Reserva
    private Reserva reserva;

    @Column
    private String posicao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
}
