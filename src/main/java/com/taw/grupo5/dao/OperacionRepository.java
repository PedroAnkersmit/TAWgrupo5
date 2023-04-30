package com.taw.grupo5.dao;

import com.taw.grupo5.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperacionRepository extends JpaRepository<OperacionEntity, Integer> {
    @Query("select o from OperacionEntity o where o.idcliente = :idCliente")
    List<OperacionEntity> buscarPorCliente(@Param("idCliente") Integer idCliente);
}
