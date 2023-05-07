package com.taw.grupo5.service;

import com.taw.grupo5.dao.OperacionRepository;
import com.taw.grupo5.dao.SacarDineroRepository;
import com.taw.grupo5.dao.TransferenciaRepository;
import com.taw.grupo5.dto.SacardineroDTO;
import com.taw.grupo5.dto.TransferenciaDTO;
import com.taw.grupo5.entity.SacardineroEntity;
import com.taw.grupo5.entity.TransferenciaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {

    @Autowired
    protected TransferenciaRepository transferenciaRepository;
    @Autowired
    protected OperacionRepository operacionRepository;

    public void guardar(TransferenciaDTO dto){

        TransferenciaEntity sd = new TransferenciaEntity();

        sd.setCantidad(dto.getCantidad());
        sd.setOperacionByIdoperacion(operacionRepository.findById(dto.getOperacion()).orElse(null));
        sd.setIdcuentadestino(dto.getIdCuentaDestino());
        sd.setFechainstruccion(dto.getFechaInstruccion());
        sd.setFechaejecucion(dto.getFechaEjecucion());
        sd.setIdTransferencia(dto.getIdTransferencia());

        transferenciaRepository.save(sd);

    }
}








