package com.taw.grupo5.dao;

import com.taw.grupo5.entity.ClienteEntity;
import com.taw.grupo5.entity.ConversacionEntity;
import com.taw.grupo5.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * @author Hilaria Romero Bouyahia
 */
public interface ConversacionRepository extends JpaRepository<ConversacionEntity, Integer> {
    @Query("select c from ConversacionEntity c where c.clienteByIdcliente = :cliente")
    List<ConversacionEntity> buscarPorCliente(@Param("cliente") ClienteEntity cliente);

    //"001-solo asunto"
    @Query("select c from ConversacionEntity c where c.asunto like CONCAT('%', :asunto, '%')")
    List<ConversacionEntity> filtrarAsunto(@Param("asunto") String asunto);

    //"010-solo abierto"
    @Query("select c from ConversacionEntity c where c.abierto = :abierto")
    List<ConversacionEntity> filtrarAbierto(@Param("abierto") Byte abierto);

    //"011- abierto y asunto"
    @Query("select c from ConversacionEntity c where " +
            "c.asunto like CONCAT('%', :asunto, '%') and " +
            "c.abierto=:abierto")
    List<ConversacionEntity> filtrarAbiertoAsunto(@Param("abierto") Byte abierto, @Param("asunto") String asunto);

    //"100- solo nombre"
    @Query("select c from ConversacionEntity c where " +
            "c.clienteByIdcliente.email like CONCAT('%', :nombreOCorreo, '%') " +
            "or c.clienteByIdcliente.nombre like CONCAT('%', :nombreOCorreo, '%')")
    List<ConversacionEntity> filtrarNombreOCorreo(@Param("nombreOCorreo") String nombreOCorreo);

    //"101- nombre y asunto"
    @Query("select c from ConversacionEntity c where " +
            "c.asunto like CONCAT('%', :asunto, '%') and " +
            "(c.clienteByIdcliente.email like CONCAT('%', :nombreOCorreo, '%') or " +
            "c.clienteByIdcliente.nombre like CONCAT('%', :nombreOCorreo, '%'))")
    List<ConversacionEntity> filtrarNombreOCorreoAsunto(@Param("nombreOCorreo") String nombreOCorreo, @Param("asunto") String asunto);

    //"110- nombre y abierto"
    @Query("select c from ConversacionEntity c where " +
            "c.abierto=:abierto and " +
            "(c.clienteByIdcliente.email like CONCAT('%', :nombreOCorreo, '%') or " +
            "c.clienteByIdcliente.nombre like CONCAT('%', :nombreOCorreo, '%'))")
    List<ConversacionEntity> filtrarNombreOCorreoAbierto(@Param("nombreOCorreo") String nombreOCorreo, @Param("abierto") Byte abierto);

    //"111- todos: nombre, abierto y asunto"
    @Query("select c from ConversacionEntity c where " +
            "c.abierto=:abierto and " +
            "c.asunto like CONCAT('%', :asunto, '%') and " +
            "(c.clienteByIdcliente.email like CONCAT('%', :nombreOCorreo, '%') or " +
            "c.clienteByIdcliente.nombre like CONCAT('%', :nombreOCorreo, '%'))")
    List<ConversacionEntity> filtrarTodo(@Param("nombreOCorreo") String nombreOCorreo, @Param("abierto") Byte abierto, @Param("asunto") String asunto);

}
