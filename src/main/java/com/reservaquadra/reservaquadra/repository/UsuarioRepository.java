package com.reservaquadra.reservaquadra.repository;

import java.util.List;

import com.reservaquadra.reservaquadra.dto.responseDto.UsuarioResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reservaquadra.reservaquadra.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<Usuario> findByNomeContainingIgnoreCase(@Param("nome") String nome);
}
