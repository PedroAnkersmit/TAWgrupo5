package com.taw.grupo5.dao;

import com.taw.grupo5.entity.ClienteEntity;
import com.taw.grupo5.entity.ConversacionEntity;
import com.taw.grupo5.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversacionRepository extends JpaRepository<ConversacionEntity, Integer> {
    @Query("select c from ConversacionEntity c where c.clienteByIdcliente = :cliente")
    List<ConversacionEntity> buscarPorCliente(ClienteEntity cliente);


}
