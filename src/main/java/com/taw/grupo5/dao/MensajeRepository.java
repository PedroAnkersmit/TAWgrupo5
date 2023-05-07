package com.taw.grupo5.dao;

import com.taw.grupo5.entity.ConversacionEntity;
import com.taw.grupo5.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * @author Hilaria Romero Bouyahia
 */
public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {
    @Query("select m from MensajeEntity m where m.conversacionByIdconversacion=:conversacion and m.enviadoporasistente<1")
    List<MensajeEntity> moderarMensajesCliente(@Param("conversacion") ConversacionEntity conversacion);


}
