package com.taw.grupo5.dao;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OperacionesRepository extends JpaRepository<OperacionEntity, Integer> {
    @Query("select o from OperacionEntity o where o.idcliente = :idcliente")
    List<OperacionEntity> buscarPorCliente(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity  o where o.cambiodivisaByIdoperacion is not null and o.transferenciaByIdoperacion is null and o.idcliente = :idcliente")
    List<OperacionEntity> buscarCambioDivisa(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity  o where o.sacardineroByIdoperacion is not null and o.sacardineroByIdoperacion.cantidad >= :quantity and o.idcliente = :idcliente")
    List<OperacionEntity> buscarSacarDinero(@Param("quantity") BigDecimal cantidad, @Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity  o where o.transferenciaByIdoperacion is not null and o.transferenciaByIdoperacion.cantidad >= :quantity and o.idcliente = :idcliente")
    List<OperacionEntity> buscarTransferencia(@Param("quantity") BigDecimal cantidad, @Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity o where o.idcliente = :idcliente and o.transferenciaByIdoperacion.cantidad >= :quantity or o.sacardineroByIdoperacion.cantidad >= :quantity or o.cambiodivisaByIdoperacion.cantidadventa >= :quantity")
    List<OperacionEntity> buscarPorCantidad(@Param("quantity") BigDecimal cantidad, @Param("idcliente") Integer idcliente);



}
