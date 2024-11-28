package com.reservaquadra.reservaquadra.controller.handlerException;

import com.reservaquadra.reservaquadra.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ManipuladorException {

    @ExceptionHandler(EncerrarPartidaException.class)
    public ResponseEntity<PadraoDeErro> encerrarPartidaException(EncerrarPartidaException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.OK;
        String erro = "Partida encerrada.";
        PadraoDeErro padraoDeErro = new PadraoDeErro(LocalDateTime.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(padraoDeErro);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<PadraoDeErro> entidadeNaoEncontradaExceptionResponseEntity(EntidadeNaoEncontradaException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String erro = "Entidade não encontrada.";
        PadraoDeErro padraoDeErro = new PadraoDeErro(LocalDateTime.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(padraoDeErro);
    }

    @ExceptionHandler(StatusDeJogadorNaoExisteException.class)
    public ResponseEntity<PadraoDeErro> statusDeJogadorNaoExisteException(StatusDeJogadorNaoExisteException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String erro = "Verifique o formato da requisição, o status informado não existe.";
        PadraoDeErro padraoDeErro = new PadraoDeErro(LocalDateTime.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(padraoDeErro);
    }

    @ExceptionHandler(UsuarioContemUmaEquipeException.class)
    public ResponseEntity<PadraoDeErro> usuarioContemUmaEquipeException(UsuarioContemUmaEquipeException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String erro = "Usuário já está registrado em uma equipe.";
        PadraoDeErro padraoDeErro = new PadraoDeErro(LocalDateTime.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(padraoDeErro);
    }

    @ExceptionHandler(UsuarioJaEstaNaEquipeException.class)
    public ResponseEntity<PadraoDeErro> usuarioJaEstaNaEquipeException(UsuarioJaEstaNaEquipeException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String erro = "Usuário já está cadastrado na equipe que você deseja.";
        PadraoDeErro padraoDeErro = new PadraoDeErro(LocalDateTime.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(padraoDeErro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PadraoDeErro> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String erro = "Erro ao validar argumento, verifique sua requisição novamente.";
        PadraoDeErro padraoDeErro = new PadraoDeErro(LocalDateTime.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(padraoDeErro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<PadraoDeErro> dataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String erro = "Não foi possível executar a requisição, erro interno no banco de dados.";
        PadraoDeErro padraoDeErro = new PadraoDeErro(LocalDateTime.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(padraoDeErro);
    }

    @ExceptionHandler(ConflitoNoAluguelException.class)
    public ResponseEntity<PadraoDeErro> conflitoNoAluguelException(ConflitoNoAluguelException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String erro = "Não foi possível efetuar uma reserva, horário já está ocupado.";
        PadraoDeErro padraoDeErro = new PadraoDeErro(LocalDateTime.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(padraoDeErro);
    }

    @ExceptionHandler(ConflitoDataHoraException.class)
    public ResponseEntity<PadraoDeErro> conflitoNoAluguelException(ConflitoDataHoraException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String erro = "Horario de inicio não pode ser após o encerramento.";
        PadraoDeErro padraoDeErro = new PadraoDeErro(LocalDateTime.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(padraoDeErro);
    }
}
