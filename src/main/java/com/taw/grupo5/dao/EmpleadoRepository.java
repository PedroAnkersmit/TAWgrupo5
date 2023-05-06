package com.taw.grupo5.dao;

import com.taw.grupo5.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Integer> {

    @Query("Select a from EmpleadoEntity a where a.tipoempleadoByIdtipoempleado.idtipoempleado=2")
    List<EmpleadoEntity> listarAsistentes();
}

