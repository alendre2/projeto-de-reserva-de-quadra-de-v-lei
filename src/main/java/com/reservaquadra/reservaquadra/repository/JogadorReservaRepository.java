package com.reservaquadra.reservaquadra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservaquadra.reservaquadra.entity.JogadorReserva;

@Repository
public interface JogadorReservaRepository extends JpaRepository<JogadorReserva, Long> {

}
