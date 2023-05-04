package com.taw.grupo5.service;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.dao.*;
import com.taw.grupo5.dto.ClienteDTO;
import com.taw.grupo5.dto.CuentaDTO;
import com.taw.grupo5.dto.TipoclienteDTO;
import com.taw.grupo5.dto.TipoestadoDTO;
import com.taw.grupo5.entity.ClienteEntity;
import com.taw.grupo5.entity.CuentaEntity;
import com.taw.grupo5.entity.TipoclienteEntity;
import com.taw.grupo5.entity.TipoestadoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected TipoClienteRepository tipoClienteRepository;
    @Autowired
    protected TipoEstadoRepository tipoEstadoRepository;
    @Autowired
    protected CuentaRepository cuentaRepository;
    @Autowired
    protected EmpresaRepository empresaRepository;

    public ClienteDTO doBuscarUsuario(String correo){
        ClienteEntity usuario = clienteRepository.buscarCuenta(correo);
        return (usuario == null? null : usuario.toDTO());
    }

    public TipoestadoDTO doBuscarTipoEstado(Integer id){
        TipoestadoEntity tipoestado = tipoEstadoRepository.getById(id);
        return tipoestado.toDTO();
    }

    public TipoclienteDTO doBuscarTipoCliente(Integer id){
        TipoclienteEntity tipocliente = tipoClienteRepository.getById(id);
        return tipocliente.toDTO();
    }

    public void GuardarClienteYCuenta(ClienteDTO clienteDTO, CuentaDTO cuentaDTO){
        ClienteEntity cliente = new ClienteEntity();
        CuentaEntity cuenta = new CuentaEntity();
        List<CuentaEntity> cuentas = new ArrayList<>();

        cuenta.setIdcuenta(cuentaDTO.getIdcuenta());
        cuenta.setFechaapertura(cuentaDTO.getFechaapertura());
        cuenta.setNumerocuenta(cuentaDTO.getNumerocuenta());
        cuenta.setSaldo(cuentaDTO.getSaldo());
        cuenta.setFechacierre(cuentaDTO.getFechacierre());
        cuenta.setTipoestadoByIdestado(tipoEstadoRepository.getById(cuentaDTO.getTipoEstado().getIdTipoestado()));
        cuenta.setOperacionsByIdcuenta(new ArrayList<>());
        cuenta.setClienteByIdcliente(cliente);
        cuentaRepository.save(cuenta);
        cuentas.add(cuenta);

        cliente.setIdcliente(clienteDTO.getIdCliente());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setFechainicio(clienteDTO.getFechainicio());
        cliente.setIdconversacion(clienteDTO.getIdConversacion());
        cliente.setTipoclienteByIdtipocliente(tipoClienteRepository.findById(clienteDTO.getTipoCliente().getIdTipocliente()).orElse(null));
        cliente.setEmpresaByIdempresa(null);
        cliente.setCuentasByIdcliente(cuentas);
        cliente.setConversacionsByIdcliente(new ArrayList<>());
        clienteRepository.save(cliente);



    }

}
