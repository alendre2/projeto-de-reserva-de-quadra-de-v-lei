package com.reservaquadra.reservaquadra.repository;

import com.reservaquadra.reservaquadra.entity.Aluguel;
import com.reservaquadra.reservaquadra.enums.StatusAluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

    @Query("SELECT a FROM Aluguel a WHERE a.statusAluguel = :statusAluguel")
    List<Aluguel> findByStatusAluguel(@Param("statusAluguel")StatusAluguel statusAluguel);
}