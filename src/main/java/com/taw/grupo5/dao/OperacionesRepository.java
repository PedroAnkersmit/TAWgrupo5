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

    @Query("select o from OperacionEntity  o where o.cambiodivisaByIdoperacion is not null and o.transferenciaByIdoperacion is null and o.sacardineroByIdoperacion is null and o.idcliente = :idcliente")
    List<OperacionEntity> buscarCambioDivisa(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity  o where o.sacardineroByIdoperacion is not null  and o.cambiodivisaByIdoperacion is null  and  o.transferenciaByIdoperacion is null and o.idcliente = :idcliente")
    List<OperacionEntity> buscarSacarDinero( @Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity  o where o.transferenciaByIdoperacion is not null  and o.cambiodivisaByIdoperacion is null and o.sacardineroByIdoperacion is null and o.idcliente = :idcliente")
    List<OperacionEntity> buscarTransferencia(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity o where o.idcliente = :idcliente and (o.cambiodivisaByIdoperacion is not null or (o.sacardineroByIdoperacion is not null ))")
    List<OperacionEntity> buscarCambioDivisaSacarDinero(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity o where o.idcliente = :idcliente and (o.cambiodivisaByIdoperacion is not null or (o.transferenciaByIdoperacion is not null ))")
    List<OperacionEntity> buscarCambioDivisaTransferencia(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity o where o.idcliente = :idcliente and (o.sacardineroByIdoperacion is not null  or o.transferenciaByIdoperacion is not null )")
    List<OperacionEntity> buscarSacarDineroTransferencia(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity o where o.idcliente = :idcliente")
    List<OperacionEntity> buscarTodas(@Param("idcliente") Integer idcliente);




}
