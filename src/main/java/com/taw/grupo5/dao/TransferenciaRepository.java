package com.taw.grupo5.dao;

import com.taw.grupo5.entity.SacardineroEntity;
import com.taw.grupo5.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer> {

    @Query("select s from TransferenciaEntity s where s.operacionByIdoperacion.idoperacion = :id")
    List<TransferenciaEntity> buscarPorIdOperacion(@Param("id") Integer idOperacion);

}
