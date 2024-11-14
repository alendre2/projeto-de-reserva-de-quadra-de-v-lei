package com.reservaquadra.reservaquadra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservaquadra.reservaquadra.entity.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	}
