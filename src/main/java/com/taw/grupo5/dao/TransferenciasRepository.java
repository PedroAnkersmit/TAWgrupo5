package com.taw.grupo5.dao;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.entity.OperacionEntity;
import com.taw.grupo5.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TransferenciasRepository extends JpaRepository<TransferenciaEntity, Integer> {
    @Query("select t.operacionByIdoperacion from TransferenciaEntity t where t.operacionByIdoperacion.idcliente = :id and t.cantidad >= :quantity")
    List<OperacionEntity> BuscarPorCliente(@Param("id") Integer idCliente, @Param("quantity") BigDecimal cantidad);
}
