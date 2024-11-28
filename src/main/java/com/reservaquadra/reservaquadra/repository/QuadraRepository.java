package com.reservaquadra.reservaquadra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reservaquadra.reservaquadra.entity.Quadra;

@Repository
public interface QuadraRepository extends JpaRepository<Quadra, Long> {

	List<Quadra> findByNome(String nome);

	@Query("SELECT q FROM Quadra q WHERE q.tipo = :tipo")
	List<Quadra> findByTipo(@Param("tipo") String tipo);
}
