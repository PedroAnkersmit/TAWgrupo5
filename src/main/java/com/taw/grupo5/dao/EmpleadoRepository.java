package com.taw.grupo5.dao;

import com.taw.grupo5.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Integer> {
    @Query("Select a from EmpleadoEntity a where a.tipoempleadoByIdtipoempleado.idtipoempleado=2")
    List<EmpleadoEntity> listarAsistentes();

    @Query("Select e from EmpleadoEntity e where e.nombre = :nombre")
    EmpleadoEntity logear(@Param("nombre") String nombre);
}

