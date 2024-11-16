package com.reservaquadra.reservaquadra.exception;

import java.io.Serial;
import java.io.Serializable;

public class StatusDeJogadorNaoExisteException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public StatusDeJogadorNaoExisteException(String msg) {
        super(msg);
    }
}
