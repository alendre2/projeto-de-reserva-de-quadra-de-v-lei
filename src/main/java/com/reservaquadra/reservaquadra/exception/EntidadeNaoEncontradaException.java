package com.reservaquadra.reservaquadra.exception;

import java.io.Serial;
import java.io.Serializable;

public class EntidadeNaoEncontradaException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
