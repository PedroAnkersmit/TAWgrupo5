package com.taw.grupo5.dao;

import com.taw.grupo5.entity.ConversacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversacionRepository extends JpaRepository<ConversacionEntity, Integer> {
}
