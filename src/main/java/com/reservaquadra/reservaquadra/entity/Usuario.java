package com.reservaquadra.reservaquadra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reservaquadra.reservaquadra.enums.TipoUsuario;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 14)
    private String contato;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    public Usuario() {
    }

    public Usuario(String nome, String email, String contato, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.contato = contato;
        this.tipoUsuario = tipoUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @JsonIgnore
    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
