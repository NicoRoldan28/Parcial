package com.utn.parcial.repository;

import com.utn.parcial.model.Cumpleanitos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CumpleanitosRepository extends JpaRepository<Cumpleanitos,Integer> {
}
