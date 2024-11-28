package com.reservaquadra.reservaquadra.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @ManyToMany(mappedBy = "quadras")
    private final List<Aluguel> aluguels = new ArrayList<>();

    public Quadra() {
    }

    public Quadra(Long id, String nome, String tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
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
