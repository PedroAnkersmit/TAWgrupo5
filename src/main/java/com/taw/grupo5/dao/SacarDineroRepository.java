package com.taw.grupo5.dao;/*
Created by Pedro Ankersmit Carrión
*/


import com.taw.grupo5.entity.SacardineroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SacarDineroRepository extends JpaRepository<SacardineroEntity, Integer> {
    @Query("select s from SacardineroEntity s where s.operacionByIdoperacion.idcliente = :id")
    List<SacardineroEntity> BuscarPorCliente(@Param("id") Integer idCliente);
}
