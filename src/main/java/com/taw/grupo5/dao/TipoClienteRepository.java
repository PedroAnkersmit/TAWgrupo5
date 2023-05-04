package com.taw.grupo5.dao;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.entity.TipoclienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoClienteRepository  extends JpaRepository<TipoclienteEntity, Integer> {
}
