package com.reservaquadra.reservaquadra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservaquadra.reservaquadra.entity.Quadra;

@Repository
public interface QuadraRepository extends JpaRepository<Quadra, Long> {

	
	List<Quadra> findByNome(String nome);
}
