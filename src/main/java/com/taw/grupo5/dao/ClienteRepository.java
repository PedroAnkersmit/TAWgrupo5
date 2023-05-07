package com.taw.grupo5.dao;

import com.taw.grupo5.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer>{
    //Pedro
    @Query("select c from ClienteEntity c where c.nombre like CONCAT('%', :filtro, '%') or c.email like CONCAT('%', :filtro, '%')")
    List<ClienteEntity> buscarPorNombre (@Param("filtro") String filtro);

    //Hilaria
    @Query("select c from ClienteEntity c where c.email = :emailCliente")
    ClienteEntity autenticar(String emailCliente);

    //Pedro
    @Query("select c from ClienteEntity c where  c.email like CONCAT('%', :mail, '%')")
    ClienteEntity buscarCuenta ( @Param("mail") String correo);

    //Jesus
    @Query("select c from ClienteEntity c where c.empresaByIdempresa.idempresa = :filtro")
    List<ClienteEntity> buscarPorEmpresa(@Param("filtro") Integer filtro);

    // Ignacio Martínez Gallardo
    @Query("select c.clienteByIdcliente from CuentaEntity c where c.tipoestadoByIdestado.idtipoestado = 1")
    List<ClienteEntity> listadoClientesDarAlta();

    // Ignacio Martínez Gallardo
    @Query("select distinct o.cuentaByIdcuenta.clienteByIdcliente from OperacionEntity o where datediff(curdate(), o.fecha) >= 30 and o.cuentaByIdcuenta.tipoestadoByIdestado.idtipoestado = 2")
    List<ClienteEntity> listadoClientesInactivos();

    // Ignacio Martínez Gallardo
    @Query("select c.clienteByIdcliente from CuentaEntity c where c.tipoestadoByIdestado.idtipoestado = 3")
    List<ClienteEntity> listadoClientesDesbloqueo();
}
