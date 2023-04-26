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

    @Query("select o from OperacionEntity  o where o.cambiodivisaByIdoperacion is not null and o.cambiodivisaByIdoperacion.cantidadventa >= :quantity")
    List<OperacionEntity> buscarCambioDivisa(@Param("quantity") BigDecimal cantidad);

    @Query("select o from OperacionEntity  o where o.cambiodivisaByIdoperacion is not null and o.cambiodivisaByIdoperacion.cantidadventa >= :quantity and o.fecha = getdate(:date)")
    List<OperacionEntity> buscarCambioDivisaPorFecha(@Param("quantity") BigDecimal cantidad, @Param("date") String fecha);

    @Query("select o from OperacionEntity  o where o.sacardineroByIdoperacion is not null and o.sacardineroByIdoperacion.cantidad >= :quantity")
    List<OperacionEntity> buscarSacarDinero(@Param("quantity") BigDecimal cantidad);

    @Query("select o from OperacionEntity  o where o.sacardineroByIdoperacion is not null and o.sacardineroByIdoperacion.cantidad >= :quantity and o.fecha = getdate(:date)")
    List<OperacionEntity> buscarSacarDineroPorFecha(@Param("quantity") BigDecimal cantidad, @Param("date") String fecha);
    @Query("select o from OperacionEntity  o where o.transferenciaByIdoperacion is not null and o.transferenciaByIdoperacion.cantidad >= :quantity")
    List<OperacionEntity> buscarTransferencia(@Param("quantity") BigDecimal cantidad);

    @Query("select o from OperacionEntity  o where o.transferenciaByIdoperacion is not null and o.transferenciaByIdoperacion.cantidad >= :quantity and o.fecha = getdate( :date)")
    List<OperacionEntity> buscarTransferenciaPorFecha(@Param("quantity") BigDecimal cantidad, @Param("date") String fecha);



    @Query("select o from OperacionEntity  o where o.fecha = getdate(:date) and (o.transferenciaByIdoperacion.cantidad >= :quantity or o.sacardineroByIdoperacion.cantidad >= :quantity or o.cambiodivisaByIdoperacion.cantidadventa >= :quantity)")
    List<OperacionEntity> buscarPorFecha(@Param("date") String fecha, @Param("quantity") BigDecimal cantidad);
}
