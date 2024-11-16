package com.reservaquadra.reservaquadra.repository;

import java.util.List;

import com.reservaquadra.reservaquadra.dto.responseDto.UsuarioResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservaquadra.reservaquadra.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<UsuarioResponseDto> findByNomeContainingIgnoreCase(String nome);
}
