package com.taw.grupo5.dao;

import com.taw.grupo5.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperacionRepository extends JpaRepository<OperacionEntity, Integer> {

    @Query("select o from OperacionEntity o where o.idcliente = :idcliente")
    List<OperacionEntity> buscarPorCliente(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity  o where o.cambiodivisasByIdoperacion is not empty and o.transferenciasByIdoperacion is empty and o.sacardinerosByIdoperacion is empty and o.idcliente = :idcliente")
    List<OperacionEntity> buscarCambioDivisa(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity  o where o.sacardinerosByIdoperacion is not empty and o.cambiodivisasByIdoperacion is empty  and  o.transferenciasByIdoperacion is empty and o.idcliente = :idcliente")
    List<OperacionEntity> buscarSacarDinero( @Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity  o where o.transferenciasByIdoperacion is not empty  and o.cambiodivisasByIdoperacion is empty and o.sacardinerosByIdoperacion is empty and o.idcliente = :idcliente")
    List<OperacionEntity> buscarTransferencia(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity o where o.idcliente = :idcliente and (o.cambiodivisasByIdoperacion is not empty or (o.sacardinerosByIdoperacion is not empty ))")
    List<OperacionEntity> buscarCambioDivisaSacarDinero(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity o where o.idcliente = :idcliente and (o.cambiodivisasByIdoperacion is not empty or (o.transferenciasByIdoperacion is not empty ))")
    List<OperacionEntity> buscarCambioDivisaTransferencia(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity o where o.idcliente = :idcliente and (o.sacardinerosByIdoperacion is not empty  or o.transferenciasByIdoperacion is not empty )")
    List<OperacionEntity> buscarSacarDineroTransferencia(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity o where o.idcliente = :idcliente")
    List<OperacionEntity> buscarTodas(@Param("idcliente") Integer idcliente);

    @Query("select o from OperacionEntity  o where o.cambiodivisasByIdoperacion is not empty and o.transferenciasByIdoperacion is empty and o.sacardinerosByIdoperacion is empty and o.cuentaByIdcuenta.idcuenta = :idcuenta")
    List<OperacionEntity> buscarCambioDivisaPorCuenta(@Param("idcuenta") Integer idCuenta);

    @Query("select o from OperacionEntity  o where o.sacardinerosByIdoperacion is not empty and o.cambiodivisasByIdoperacion is empty  and  o.transferenciasByIdoperacion is empty and o.cuentaByIdcuenta.idcuenta = :idcuenta")
    List<OperacionEntity> buscarSacarDineroPorCuenta( @Param("idcuenta") Integer idCuenta);

    @Query("select o from OperacionEntity  o where o.transferenciasByIdoperacion is not empty  and o.cambiodivisasByIdoperacion is empty and o.sacardinerosByIdoperacion is empty and o.cuentaByIdcuenta.idcuenta = :idcuenta")
    List<OperacionEntity> buscarTransferenciaPorCuenta(@Param("idcuenta") Integer idCuenta);

}
