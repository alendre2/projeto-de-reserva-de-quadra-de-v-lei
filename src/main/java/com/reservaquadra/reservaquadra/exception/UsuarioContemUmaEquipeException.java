package com.reservaquadra.reservaquadra.exception;

import java.io.Serial;
import java.io.Serializable;

public class UsuarioContemUmaEquipeException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public UsuarioContemUmaEquipeException(String msg) {
        super(msg);
    }
}
