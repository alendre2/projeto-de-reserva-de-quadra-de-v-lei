package com.reservaquadra.reservaquadra.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @ManyToOne
    @JoinColumn(name = "quadra_id")
    private Quadra quadra;

    @OneToMany
    private final List<Equipe> equipes = new ArrayList<>();

    public Aluguel() {
    }

    public Aluguel(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal, Usuario usuario, Quadra quadra) {
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFinal = dataHoraFinal;
        this.valor = valorAluguel(dataHoraInicio, dataHoraFinal);
        this.usuario = usuario;
        this.quadra = quadra;
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

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public BigDecimal valorAluguel(LocalDateTime inicio, LocalDateTime fim) {
        long converterParaMinutos = Duration.between(inicio, fim).toMinutes();
        double converterParaHora = converterParaMinutos / 60.0;
        LocalTime quatroDaTarde = LocalTime.of(16, 0);

        if (inicio.toLocalTime().isBefore(quatroDaTarde) || fim.toLocalTime().isBefore(quatroDaTarde)) {
            return BigDecimal.valueOf(converterParaHora * 70.0);
        }
        return BigDecimal.valueOf(converterParaHora * 80.0);
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
