package com.reservaquadra.reservaquadra.repository;

import com.reservaquadra.reservaquadra.entity.EnderecoQuadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnderecoQuadraRepository extends JpaRepository<EnderecoQuadra, Long> {

    @Query("SELECT e FROM EnderecoQuadra e WHERE e.cep = :cep")
    EnderecoQuadra findByCep(@Param("cep") String cep);

    boolean existsByCep(String cep);
}
