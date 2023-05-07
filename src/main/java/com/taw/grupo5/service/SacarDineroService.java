package com.taw.grupo5.service;

import com.taw.grupo5.dao.OperacionRepository;
import com.taw.grupo5.dao.SacarDineroRepository;
import com.taw.grupo5.dto.OperacionDTO;
import com.taw.grupo5.dto.SacardineroDTO;
import com.taw.grupo5.entity.SacardineroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pablo
 */

@Service
public class SacarDineroService {

    @Autowired
    protected SacarDineroRepository sacarDineroRepository;
    @Autowired
    protected OperacionRepository operacionRepository;

    public void guardar(SacardineroDTO dto){

        SacardineroEntity sd = new SacardineroEntity();

        sd.setCantidad(dto.getCantidad());
        sd.setOperacionByIdoperacion(operacionRepository.findById(dto.getOperacion()).orElse(null));
        sd.setIdSacarDinero(dto.getIdSacarDinero());

        sacarDineroRepository.save(sd);

    }

}
