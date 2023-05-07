package com.taw.grupo5.service;

import com.taw.grupo5.dao.TipoEstadoRepository;
import com.taw.grupo5.dto.TipoestadoDTO;
import com.taw.grupo5.entity.TipoestadoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pablo
 */

@Service
public class TipoEstadoService {

    @Autowired
    protected TipoEstadoRepository tipoEstadoRepository;

    public TipoestadoDTO buscarPorId(Integer id){

        TipoestadoEntity te = tipoEstadoRepository.findById(id).orElse(null);

        if(te != null){
            return te.toDTO();
        } else {
            return null;
        }


    }

}
