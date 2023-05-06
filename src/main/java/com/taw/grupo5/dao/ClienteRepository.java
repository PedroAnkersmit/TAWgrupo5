package com.taw.grupo5.dao;

import com.taw.grupo5.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer>{

    @Query("select c from ClienteEntity  c where c.nombre like CONCAT('%', :filtro, '%') or c.email like CONCAT('%', :filtro, '%')")
    List<ClienteEntity> buscarPorNombre (@Param("filtro") String filtro);

    @Query("select c from ClienteEntity c where  c.email like CONCAT('%', :mail, '%')")
    ClienteEntity buscarCuenta ( @Param("mail") String correo);
}
