package com.taw.grupo5.dao;

import com.taw.grupo5.entity.ClienteEntity;
import com.taw.grupo5.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Jesús Ariza
 */

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Integer> {

    //Ignacio
    @Query("select e from EmpresaEntity e where e.nombre like CONCAT('%', :filtro, '%')")
    List<EmpresaEntity> buscarPorNombre (@Param("filtro") String filtro);
}
