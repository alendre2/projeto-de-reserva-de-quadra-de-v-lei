package com.reservaquadra.reservaquadra.controller.handlerException;

import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ManipuladorException {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<PadraoDeErro> entidadeNaoEncontradaExceptionResponseEntity(EntidadeNaoEncontradaException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String erro = "Entidade não encontrada.";
        PadraoDeErro padraoDeErro = new PadraoDeErro(LocalDateTime.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(padraoDeErro);
    }
}
