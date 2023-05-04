package com.taw.grupo5.dao;

import com.taw.grupo5.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/*
Created by Pedro Ankersmit Carrión
*/
public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer> {
    @Query("select c from CuentaEntity c where c.clienteByIdcliente.idcliente = :id")
    List<CuentaEntity> buscarPorCliente(@Param("id") Integer idcliente);
}
