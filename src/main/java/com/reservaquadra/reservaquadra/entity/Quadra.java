package com.reservaquadra.reservaquadra.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_quadra")
public class Quadra implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String tipo;

    @ManyToOne
    private EnderecoQuadra enderecoQuadra;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "quadra")
    private List<Aluguel> aluguels;

    public Quadra() {
    }

    public Quadra(Long id, String nome, String tipo, EnderecoQuadra enderecoQuadra, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.enderecoQuadra = enderecoQuadra;
        this.usuario = usuario;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public EnderecoQuadra getEnderecoQuadra() {
        return enderecoQuadra;
    }

    public void setEnderecoQuadra(EnderecoQuadra enderecoQuadra) {
        this.enderecoQuadra = enderecoQuadra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Aluguel> getAluguels() {
        return aluguels;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Quadra quadra = (Quadra) o;
        return Objects.equals(id, quadra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
