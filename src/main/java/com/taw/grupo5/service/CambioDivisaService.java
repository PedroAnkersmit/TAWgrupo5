package com.taw.grupo5.service;

import com.taw.grupo5.dao.CambiodivisaRepository;
import com.taw.grupo5.dao.OperacionRepository;
import com.taw.grupo5.dao.SacarDineroRepository;
import com.taw.grupo5.dto.CambioDivisaDTO;
import com.taw.grupo5.dto.SacardineroDTO;
import com.taw.grupo5.entity.CambiodivisaEntity;
import com.taw.grupo5.entity.SacardineroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pablo
 */

@Service
public class CambioDivisaService {

    @Autowired
    protected CambiodivisaRepository cambiodivisaRepository;
    @Autowired
    protected OperacionRepository operacionRepository;

    public void guardar(CambioDivisaDTO dto){

        CambiodivisaEntity sd = new CambiodivisaEntity();

        sd.setOperacionByIdoperacion(operacionRepository.findById(dto.getOperacion()).orElse(null));
        sd.setComision(dto.getComision());
        sd.setCantidadventa(dto.getCantidadventa());
        sd.setCantidadcompra(dto.getCantidadcompra());
        sd.setMonedaventa(dto.getMonedaventa());
        sd.setMonedacompra(dto.getMonedacompra());
        sd.setIdCambioDivisa(dto.getIdCambioDivisa());

        cambiodivisaRepository.save(sd);

    }

}









