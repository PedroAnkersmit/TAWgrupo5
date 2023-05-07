package com.taw.grupo5.service;

import com.taw.grupo5.dao.*;
import com.taw.grupo5.dto.ClienteDTO;
import com.taw.grupo5.dto.EmpresaDTO;
import com.taw.grupo5.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pablo
 */

@Service
public class ClienteService {

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected TipoClienteRepository tipoClienteRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected ConversacionRepository conversacionRepository;

    public ClienteDTO buscarPorID(Integer id){
        ClienteEntity cliente = clienteRepository.findById(id).orElse(null);
        if(cliente != null){
            return cliente.toDTO();
        } else return null;
    }

    public void guardar(ClienteDTO dto){
        ClienteEntity cliente = new ClienteEntity();

        cliente.setFechainicio(dto.getFechainicio());
        cliente.setEmail(dto.getEmail());
        cliente.setNombre(dto.getNombre());
        cliente.setIdcliente(dto.getIdCliente());
        cliente.setIdconversacion(dto.getIdConversacion());
        if(dto.getEmpresa() != null) cliente.setEmpresaByIdempresa(this.empresaRepository.findById(dto.getEmpresa().getIdempresa()).orElse(null));
        cliente.setTipoclienteByIdtipocliente(this.tipoClienteRepository.findById(dto.getTipoCliente().getIdTipocliente()).orElse(null));
        cliente.setCuentasByIdcliente(this.cuentaRepository.buscarPorCLiente(dto.getIdCliente()));
        cliente.setConversacionsByIdcliente(this.conversacionRepository.buscarPorIdCliente(dto.getIdCliente()));
        cliente.setTelefono(dto.getTelefono());

        clienteRepository.save(cliente);
    }
































}
