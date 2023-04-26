package com.taw.grupo5.dao;

import com.taw.grupo5.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {
}
