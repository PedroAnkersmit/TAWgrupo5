package com.taw.grupo5.dao;

import com.taw.grupo5.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Integer> {
}
