package com.reservaquadra.reservaquadra.entity;

import com.reservaquadra.reservaquadra.enums.StatusJogador;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

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
    private Equipe equipe;

    @Enumerated(EnumType.STRING)
    private StatusJogador statusJogador;

    //  @ManyToMany(mappedBy = "jogadores")
    //  private List<Reserva> reservas;

    public Jogador() {
    }

    public Jogador(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
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

    public StatusJogador getPlayerStatus() {
        return statusJogador;
    }

    public void setPlayerStatus(StatusJogador statusJogador) {
        this.statusJogador = statusJogador;
    }

    //  public List<Reserva> getReservas() {
    //      return reservas;
    // }

    //  public void setReservas(List<Reserva> reservas) {
    //     this.reservas = reservas;
    //  }
}
