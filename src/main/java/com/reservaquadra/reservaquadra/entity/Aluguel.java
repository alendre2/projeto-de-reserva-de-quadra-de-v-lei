package com.reservaquadra.reservaquadra.entity;

import com.reservaquadra.reservaquadra.enums.StatusAluguel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_aluguel")
public class Aluguel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(nullable = false)
    private LocalDateTime dataHoraFinal;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private StatusAluguel statusAluguel;

    @ManyToMany
    @JoinTable(name = "tb_aluguel_quadra", joinColumns = @JoinColumn(name = "aluguel_id"), inverseJoinColumns = @JoinColumn(name = "quadra_id"))
    private final List<Quadra> quadras = new ArrayList<>();

    public Aluguel() {
    }

    public Aluguel(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal, BigDecimal valor, Usuario usuario, StatusAluguel statusAluguel) {
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFinal = dataHoraFinal;
        this.valor = valor;
        this.usuario = usuario;
        this.statusAluguel = statusAluguel;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFinal() {
        return dataHoraFinal;
    }

    public void setDataHoraFinal(LocalDateTime dataHoraFinal) {
        this.dataHoraFinal = dataHoraFinal;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusAluguel getStatusAluguel() {
        return statusAluguel;
    }

    public void setStatusAluguel(StatusAluguel statusAluguel) {
        this.statusAluguel = statusAluguel;
    }

    public List<Quadra> getQuadras() {
        return quadras;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aluguel aluguel = (Aluguel) o;
        return Objects.equals(id, aluguel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
