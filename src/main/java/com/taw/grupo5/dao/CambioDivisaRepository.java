package com.taw.grupo5.dao;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.entity.CambiodivisaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CambioDivisaRepository extends JpaRepository<CambiodivisaEntity, Integer> {
    @Query("select c from CambiodivisaEntity c where c.operacionByIdoperacion.idcliente = :id")
    List<CambiodivisaEntity> BuscarPorCliente(@Param("id") Integer idCliente);
}
