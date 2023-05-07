package com.taw.grupo5.dao;

import com.taw.grupo5.entity.CambiodivisaEntity;
import com.taw.grupo5.entity.OperacionEntity;
import com.taw.grupo5.entity.SacardineroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;



public interface CambiodivisaRepository extends JpaRepository<CambiodivisaEntity, Integer> {

    //Jesús Ariza
    @Query("select c.operacionByIdoperacion from CambiodivisaEntity c where c.operacionByIdoperacion.idcliente = :id")
    List<OperacionEntity> BuscarPorCliente(@Param("id") Integer idCliente);

    // Pablo
    @Query("select s from CambiodivisaEntity s where s.operacionByIdoperacion.idoperacion = :id")
    List<CambiodivisaEntity> buscarPorIdOperacion(@Param("id") Integer idOperacion);

}
