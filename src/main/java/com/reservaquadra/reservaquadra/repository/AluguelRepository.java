package com.reservaquadra.reservaquadra.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservaquadra.reservaquadra.entity.Aluguel;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {


    List<Aluguel> findByDataHoraInicioBetween(LocalDateTime inicio, LocalDateTime fim);
}