package com.reservaquadra.reservaquadra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservaquadra.reservaquadra.entity.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

	
	List<Administrador> findByNome(String nome);
}
