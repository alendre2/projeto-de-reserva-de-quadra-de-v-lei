package com.reservaquadra.reservaquadra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorReservaRepository extends JpaRepository<JogadorReserva, Long> {

}
