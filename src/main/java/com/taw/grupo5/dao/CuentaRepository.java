package com.taw.grupo5.dao;

import com.taw.grupo5.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer> {
}
