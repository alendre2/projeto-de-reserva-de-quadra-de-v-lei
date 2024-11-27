package com.reservaquadra.reservaquadra.exception;

import java.io.Serial;
import java.io.Serializable;

public class EncerrarPartidaException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public EncerrarPartidaException(String msg) {
        super(msg);
    }
}
