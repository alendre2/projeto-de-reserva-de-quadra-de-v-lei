package com.reservaquadra.reservaquadra.repository;

import com.reservaquadra.reservaquadra.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
	
    List<Jogador> findByNome(String nome);  // Método para buscar Jogador por nome
}
