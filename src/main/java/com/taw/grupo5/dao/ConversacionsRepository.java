package com.taw.grupo5.dao;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.entity.ConversacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversacionsRepository extends JpaRepository<ConversacionEntity, Integer> {
    @Query("select c from ConversacionEntity c where c.clienteByIdcliente.idcliente = :id")
    List<ConversacionEntity> buscarPorCliente(@Param("id") Integer idcliente);
}
