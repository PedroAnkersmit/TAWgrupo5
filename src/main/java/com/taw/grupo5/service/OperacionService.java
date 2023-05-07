package com.taw.grupo5.service;

import com.taw.grupo5.dao.*;
import com.taw.grupo5.dto.OperacionDTO;
import com.taw.grupo5.entity.CambiodivisaEntity;
import com.taw.grupo5.entity.OperacionEntity;
import com.taw.grupo5.entity.SacardineroEntity;
import com.taw.grupo5.entity.TransferenciaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperacionService {

    @Autowired
    protected OperacionRepository operacionRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected SacarDineroRepository sacarDineroRepository;

    @Autowired
    protected TransferenciaRepository transferenciaRepository;

    @Autowired
    protected CambiodivisaRepository cambiodivisaRepository;

    public List<OperacionDTO> buscarSacarDineroPorCuenta(Integer idCuenta){

        List<OperacionDTO> dtos = new ArrayList<>();

        for (OperacionEntity o : operacionRepository.buscarSacarDineroPorCuenta(idCuenta)){
            dtos.add(o.toDTO());
        }
        return dtos;
    }

    public List<OperacionDTO> buscarTransferenciaPorCuenta(Integer idCuenta){

        List<OperacionDTO> dtos = new ArrayList<>();

        for (OperacionEntity o : operacionRepository.buscarTransferenciaPorCuenta(idCuenta)){
            dtos.add(o.toDTO());
        }
        return dtos;
    }

    public List<OperacionDTO> buscarCambioDivisaPorCuenta(Integer idCuenta){

        List<OperacionDTO> dtos = new ArrayList<>();

        for (OperacionEntity o : operacionRepository.buscarCambioDivisaPorCuenta(idCuenta)){
            dtos.add(o.toDTO());
        }
        return dtos;
    }

    public OperacionDTO ultima(){
        List<OperacionEntity> operaciones = operacionRepository.findAll();
        return operacionRepository.findById(operaciones.size()).orElse(null).toDTO();
    }

    public void guardar(OperacionDTO dto){

        OperacionEntity operacion = new OperacionEntity();

        operacion.setFecha(dto.getFecha());
        operacion.setIdcliente(dto.getCliente());
        operacion.setCuentaByIdcuenta(this.cuentaRepository.findById(dto.getCuenta()).orElse(null));
        operacion.setIdoperacion(dto.getIdOperacion());

        List<SacardineroEntity> sacardineros = sacarDineroRepository.buscarPorIdOperacion(dto.getIdOperacion());
        operacion.setSacardinerosByIdoperacion(sacardineros);

        List<TransferenciaEntity> transferencias = transferenciaRepository.buscarPorIdOperacion(dto.getIdOperacion());
        operacion.setTransferenciasByIdoperacion(transferencias);

        List<CambiodivisaEntity> cambiosDivisa = cambiodivisaRepository.buscarPorIdOperacion(dto.getIdOperacion());
        operacion.setCambiodivisasByIdoperacion(cambiosDivisa);

        operacionRepository.save(operacion);
    }

}
