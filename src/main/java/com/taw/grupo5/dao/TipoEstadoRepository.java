package com.taw.grupo5.dao;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.entity.TipoestadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEstadoRepository extends JpaRepository<TipoestadoEntity, Integer> {
}
