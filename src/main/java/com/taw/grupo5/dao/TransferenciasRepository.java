package com.taw.grupo5.dao;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciasRepository extends JpaRepository<TransferenciaEntity, Integer> {
}
