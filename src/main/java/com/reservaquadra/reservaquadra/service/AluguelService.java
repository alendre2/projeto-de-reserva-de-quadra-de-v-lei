package com.reservaquadra.reservaquadra.service;

import com.reservaquadra.reservaquadra.dto.entityDto.AluguelDto;
import com.reservaquadra.reservaquadra.entity.Aluguel;
import com.reservaquadra.reservaquadra.entity.Usuario;
import com.reservaquadra.reservaquadra.enums.StatusAluguel;
import com.reservaquadra.reservaquadra.exception.ConflitoDataHoraException;
import com.reservaquadra.reservaquadra.exception.ConflitoNoAluguelException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.mapStruct.AluguelMapStruct;
import com.reservaquadra.reservaquadra.repository.AluguelRepository;
import com.reservaquadra.reservaquadra.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AluguelService {

    private final AluguelRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final AluguelMapStruct mapStruct;
    private static final LocalTime VALOR_HORA = LocalTime.of(16, 0);

    public AluguelService(AluguelRepository repository, UsuarioRepository usuarioRepository, AluguelMapStruct mapStruct) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.mapStruct = mapStruct;
    }

    public AluguelDto criar(AluguelDto dto, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível efetuar a reserva, usuário não encontrado. Id: " + usuarioId));
        validarReserva(dto);
        validardataHora(dto);

        BigDecimal valor = valorAluguel(dto.dataHoraInicio(), dto.dataHoraFinal());

        Aluguel aluguel = new Aluguel(dto.dataHoraInicio(), dto.dataHoraFinal(), valor, usuario, dto.statusAluguel());
        return mapStruct.converterParaDto(repository.save(aluguel));
    }

    public AluguelDto atualizarHorario(Long aluguelId, AluguelDto dto) {
        return repository.findById(aluguelId)
                .map(a -> {
                    validarReserva(dto);
                    validardataHora(dto);
                    a.setDataHoraInicio(dto.dataHoraInicio());
                    a.setDataHoraFinal(dto.dataHoraFinal());
                    return mapStruct.converterParaDto(repository.save(a));
                }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível atualizar, aluguel não encontrado. Id " + aluguelId));
    }

    public AluguelDto atualizarStatusPago(Long aluguelId) {
        return repository.findById(aluguelId).map(a -> {
            a.setStatusAluguel(StatusAluguel.PAGO);
            return mapStruct.converterParaDto(repository.save(a));
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível atualizar o pagamento! Aluguel não encontrado. Id: " + aluguelId));
    }

    public AluguelDto atualizarStatusCancelado(Long aluguelId) {
        return repository.findById(aluguelId).map(a -> {
            a.setStatusAluguel(StatusAluguel.CANCELADO);
            return mapStruct.converterParaDto(repository.save(a));
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível atualizar o pagamento! Aluguel não encontrado. Id: " + aluguelId));
    }

    public List<AluguelDto> listar() {
        return mapStruct.converterListParaDto(repository.findAll());
    }

    public AluguelDto buscarPorId(Long aluguelId) {
        return mapStruct.converterParaDto(repository.findById(aluguelId).orElseThrow(() -> new EntidadeNaoEncontradaException("Aluguel não eixte. Id: " + aluguelId)));
    }

    public List<AluguelDto> aluguelCancelado(StatusAluguel statusAluguel) {
        return mapStruct.converterListParaDto(repository.findByStatusAluguel(statusAluguel));
    }

    public List<AluguelDto> aluguelPago(StatusAluguel statusAluguel) {
        return mapStruct.converterListParaDto(repository.findByStatusAluguel(statusAluguel));
    }

    //Metodo auxiliar.
    public void validarReserva(AluguelDto dto) {
        List<Aluguel> aluguels = repository.findAll();

        for (Aluguel a : aluguels) {
            if (a.getDataHoraInicio().isBefore(dto.dataHoraFinal()) && a.getDataHoraFinal().isAfter(dto.dataHoraInicio()) && a.getStatusAluguel() != StatusAluguel.CANCELADO) {
                throw new ConflitoNoAluguelException("TestConflitoAluguel");
            }
        }
    }

    //Metodo auxiliar
    public void validardataHora(AluguelDto dto) {
        if (dto.dataHoraInicio().isAfter(dto.dataHoraFinal()) || dto.dataHoraFinal().isBefore(dto.dataHoraInicio())) {
            throw new ConflitoDataHoraException("Conflito entre os horários da reserva.");
        }
    }

    //Metodo auxiliar
    public BigDecimal valorAluguel(LocalDateTime inicio, LocalDateTime fim) {
        long converterParaMinutos = Duration.between(inicio, fim).toMinutes();
        double converterParaHora = converterParaMinutos / 60.0;

        if (inicio.toLocalTime().isBefore(VALOR_HORA) || fim.toLocalTime().isBefore(VALOR_HORA)) {
            return BigDecimal.valueOf(converterParaHora * 70.0);
        }
        return BigDecimal.valueOf(converterParaHora * 80.0);
    }
}
