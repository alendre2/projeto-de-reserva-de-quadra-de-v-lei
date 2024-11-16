package com.reservaquadra.reservaquadra.enums;

public enum StatusJogador {
    TITULAR(1L),
    RESERVA(2L),
    MACHUCADO(3L);

    private final long id;

    StatusJogador(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
