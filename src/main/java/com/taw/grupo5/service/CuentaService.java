package com.taw.grupo5.service;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.CuentaRepository;
import com.taw.grupo5.dao.OperacionRepository;
import com.taw.grupo5.dao.TipoEstadoRepository;
import com.taw.grupo5.dto.CuentaDTO;
import com.taw.grupo5.entity.CuentaEntity;
import com.taw.grupo5.entity.TipoestadoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected TipoEstadoRepository tipoEstadoRepository;
    @Autowired
    protected OperacionRepository operacionRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    public CuentaDTO buscarPorId(Integer id){
        CuentaEntity cuenta = cuentaRepository.findById(id).orElse(null);

        if(cuenta != null){
            return cuenta.toDTO();
        } else {
            return null;
        }

    }

    public void guardar(CuentaDTO dto){

        CuentaEntity cuenta = new CuentaEntity();

        cuenta.setSaldo(dto.getSaldo());
        cuenta.setTipoestadoByIdestado(this.tipoEstadoRepository.findById(dto.getTipoEstado().getIdTipoestado()).orElse(null));
        cuenta.setNumerocuenta(dto.getNumerocuenta());
        cuenta.setFechaapertura(dto.getFechaapertura());
        cuenta.setIdcuenta(dto.getIdcuenta());
        cuenta.setFechacierre(dto.getFechacierre());
        cuenta.setOperacionsByIdcuenta(this.operacionRepository.buscarTodas(dto.getIdcuenta()));
        cuenta.setClienteByIdcliente(this.clienteRepository.findById(dto.getCliente().getIdCliente()).orElse(null));

        cuentaRepository.save(cuenta);

    }

}
