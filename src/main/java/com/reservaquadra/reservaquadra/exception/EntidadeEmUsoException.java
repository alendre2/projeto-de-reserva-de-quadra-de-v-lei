package com.reservaquadra.reservaquadra.exception;

import java.io.Serial;
import java.io.Serializable;

public class EntidadeEmUsoException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(String msg) {
        super(msg);
    }
}
