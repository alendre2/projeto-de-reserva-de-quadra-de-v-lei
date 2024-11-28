package com.reservaquadra.reservaquadra.enums;

public enum StatusAluguel {
    PAGO(1L),
    RESERVADO(1L),
    CANCELADO(3L);

    private final Long id;

    private StatusAluguel(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
