package com.taw.grupo5.dao;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.entity.OperacionEntity;
import com.taw.grupo5.entity.SacardineroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SacarDineroRepository extends JpaRepository<SacardineroEntity, Integer> {
    @Query("select s.operacionByIdoperacion from SacardineroEntity s where s.operacionByIdoperacion.idcliente = :id and s.cantidad >= :quantity")
    List<OperacionEntity> BuscarPorCliente(@Param("id") Integer idCliente, @Param("quantity")BigDecimal cantidad);

    // Pablo
    @Query("select s from SacardineroEntity s where s.operacionByIdoperacion.idoperacion = :id")
    List<SacardineroEntity> buscarPorIdOperacion(@Param("id") Integer idOperacion);

}
