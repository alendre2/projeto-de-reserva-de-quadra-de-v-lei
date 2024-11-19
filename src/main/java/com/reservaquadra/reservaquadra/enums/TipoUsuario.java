package com.reservaquadra.reservaquadra.enums;

public enum TipoUsuario {
    ADMINISTRADOR(1L),
    JOGADOR(2L);

    private final long id;

    TipoUsuario(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

