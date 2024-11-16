package com.reservaquadra.reservaquadra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservaquadra.reservaquadra.entity.Usuario;

@Repository
public interface AdministradorRepository extends JpaRepository<Usuario, Long> {

	
	List<Usuario> findByNome(String nome);
}
