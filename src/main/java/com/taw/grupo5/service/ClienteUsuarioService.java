package com.taw.grupo5.service;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.dao.*;
import com.taw.grupo5.dto.*;
import com.taw.grupo5.entity.*;
import com.taw.grupo5.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteUsuarioService {
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected CuentaRepository cuentaRepository;
    @Autowired
    protected OperacionesRepository operacionesRepository;
    @Autowired
    protected TransferenciasRepository transferenciasRepository;
    @Autowired
    protected CambioDivisaRepository cambioDivisaRepository;
    @Autowired
    protected SacarDineroRepository sacarDineroRepository;
    @Autowired
    protected TipoClienteRepository tipoClienteRepository;
    @Autowired
    protected EmpresaRepository empresaRepository;
    @Autowired
    protected ConversacionsRepository conversacionsRepository;
    @Autowired
    protected TipoEstadoRepository tipoEstadoRepository;
    public ClienteDTO doBuscarUsuario(Integer id){
        ClienteEntity usuario = clienteRepository.findById(id).orElse(null);
        return (usuario == null? null : usuario.toDTO());
    }

    public CuentaDTO doBuscarCuenta(Integer id){
        CuentaEntity cuenta = cuentaRepository.findById(id).orElse(null);
        return cuenta.toDTO();
    }

    public List<CuentaDTO> doCogerCuentas(){
        List<CuentaEntity> cuentas= cuentaRepository.findAll();
        List<CuentaDTO> cuentaDTOS = new ArrayList<>();
        for(CuentaEntity c : cuentas){
            cuentaDTOS.add(c.toDTO());
        }
        return cuentaDTOS;
    }
    public List<CuentaDTO> doBuscarCuentasPorUsuario(Integer id){
        List<CuentaEntity> cuentas= cuentaRepository.buscarPorCliente(id);
        List<CuentaDTO> cuentaDTOS = new ArrayList<>();
        for(CuentaEntity c : cuentas){
            cuentaDTOS.add(c.toDTO());
        }
        return cuentaDTOS;
    }

    public List<TransferenciaDTO> doFiltrarTransferencias(FiltroOperaciones filtro, ClienteDTO usDTO){
        List<TransferenciaEntity> list = new ArrayList<>();
        ClienteEntity usuario = clienteRepository.findById(usDTO.getIdCliente()).orElse(null);
        if(filtro.isTransferencia()){
            list = transferenciasRepository.BuscarPorCliente(usuario.getIdcliente());
        }
        List<TransferenciaDTO> transferenciaDTOS = new ArrayList<>();
        for(TransferenciaEntity t : list){
            transferenciaDTOS.add(t.toDTO());
        }
        return transferenciaDTOS;
    }
    public List<CambioDivisaDTO> doFiltrarCambios(FiltroOperaciones filtro, ClienteDTO usDTO){
        List<CambiodivisaEntity> list = new ArrayList<>();
        ClienteEntity usuario = clienteRepository.findById(usDTO.getIdCliente()).orElse(null);
        if(filtro.isCambioDivisa()){
            list = cambioDivisaRepository.BuscarPorCliente(usuario.getIdcliente());
        }
        List<CambioDivisaDTO> cambioDivisaDTOS = new ArrayList<>();
        for(CambiodivisaEntity c : list){
            cambioDivisaDTOS.add(c.toDTO());
        }
        return cambioDivisaDTOS;
    }
    public List<SacardineroDTO> doFiltrarExtracciones(FiltroOperaciones filtro, ClienteDTO usDTO){
        List<SacardineroEntity> list = new ArrayList<>();
        ClienteEntity usuario = clienteRepository.findById(usDTO.getIdCliente()).orElse(null);
        if(filtro.isSacarDinero()){
            list = sacarDineroRepository.BuscarPorCliente(usuario.getIdcliente());
        }
        List<SacardineroDTO> sacardineroDTOS = new ArrayList<>();
        for(SacardineroEntity s : list){
            sacardineroDTOS.add(s.toDTO());
        }
        return sacardineroDTOS;
    }
    public List<OperacionDTO> doFiltrar(FiltroOperaciones filtro, ClienteDTO usDTO){
        List<OperacionEntity> operaciones = new ArrayList<>();
        ClienteEntity usuario = clienteRepository.findById(usDTO.getIdCliente()).orElse(null);

        if(filtro.isCambioDivisa()&& filtro.isTransferencia() && filtro.isSacarDinero()){
            operaciones = operacionesRepository.buscarTodas(usuario.getIdcliente());
        } else if(filtro.isCambioDivisa() && filtro.isTransferencia()){
            operaciones = operacionesRepository.buscarCambioDivisaTransferencia(usuario.getIdcliente());
        } else if(filtro.isCambioDivisa() && filtro.isSacarDinero()){
            operaciones = operacionesRepository.buscarCambioDivisaSacarDinero(usuario.getIdcliente());
        } else if (filtro.isTransferencia() && filtro.isSacarDinero()) {
            operaciones = operacionesRepository.buscarSacarDineroTransferencia(usuario.getIdcliente());
        } else{
            if(filtro.isCambioDivisa() && !filtro.isTransferencia() && !filtro.isSacarDinero()) {
                operaciones = operacionesRepository.buscarCambioDivisa(usuario.getIdcliente());
            } else if (filtro.isTransferencia() && !filtro.isCambioDivisa() && !filtro.isSacarDinero() ) {


            } else if(filtro.isSacarDinero() && !filtro.isTransferencia() && !filtro.isCambioDivisa()) {
                operaciones = operacionesRepository.buscarSacarDinero(usuario.getIdcliente());
            }
        }
        List<OperacionDTO> operacionDTOS = new ArrayList<>();
        for(OperacionEntity o : operaciones){
            operacionDTOS.add(o.toDTO());
        }

        return operacionDTOS;
    }

    public void doGuardarUsuario(ClienteDTO clienteDTO){
        ClienteEntity cliente = new ClienteEntity();
        List<CuentaEntity> cuentas = cuentaRepository.buscarPorCliente(cliente.getIdcliente());


        cliente.setIdcliente(clienteDTO.getIdCliente());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setFechainicio(clienteDTO.getFechainicio());
        cliente.setIdconversacion(clienteDTO.getIdConversacion());
        cliente.setTipoclienteByIdtipocliente(tipoClienteRepository.findById(clienteDTO.getTipoCliente().getIdTipocliente()).orElse(null));
        cliente.setEmpresaByIdempresa(empresaRepository.getById(clienteDTO.getEmpresa().getIdempresa()));
        cliente.setCuentasByIdcliente(cuentas);
        cliente.setConversacionsByIdcliente(conversacionsRepository.buscarPorCliente(clienteDTO.getIdCliente()));
        clienteRepository.save(cliente);
    }

    public OperacionDTO doCrearOperacion(CuentaDTO cuentaDTO){
        OperacionEntity operacion = new OperacionEntity();
        operacion.setIdcliente(cuentaDTO.getCliente().getIdCliente());
        operacion.setCuentaByIdcuenta(cuentaRepository.getById(cuentaDTO.getIdcuenta()));
        operacion.setFecha(new Date(System.currentTimeMillis()));
        operacionesRepository.save(operacion);
        return operacion.toDTO();
    }

    public OperacionDTO doBuscarOperacion(Integer id){
        OperacionEntity op = operacionesRepository.findById(id).orElse(null);
        return op.toDTO();
    }



    public CambioDivisaDTO doGuardarCambioDivisa(CambioDivisaDTO cambio){
        CambiodivisaEntity cambiodivisa = new CambiodivisaEntity();
        cambiodivisa.setIdCambioDivisa(cambio.getIdCambioDivisa());
        cambiodivisa.setOperacionByIdoperacion(operacionesRepository.getById(cambio.getOperacion().getIdOperacion()));
        cambiodivisa.setMonedaventa("dolar");
        cambiodivisa.setMonedacompra("euro");
        cambiodivisa.setCantidadventa(cambio.getCantidadventa());
        cambiodivisa.setCantidadcompra(cambio.getCantidadcompra());
        cambiodivisa.setComision("0");
        cambioDivisaRepository.save(cambiodivisa);
        cambio.setIdCambioDivisa(cambiodivisa.getIdCambioDivisa());
        return cambio;
    }
    public TransferenciaDTO doGuardarTransferencia(TransferenciaDTO transfer){
        TransferenciaEntity transferencia = new TransferenciaEntity();

        transferencia.setIdTransferencia(transfer.getIdTransferencia());
        transferencia.setFechainstruccion(transfer.getFechaInstruccion());
        transferencia.setFechaejecucion(new Date(System.currentTimeMillis()));
        transferencia.setCantidad(transfer.getCantidad());
        transferencia.setOperacionByIdoperacion(operacionesRepository.getById(transfer.getOperacion()));
        transferenciasRepository.save(transferencia);
        transfer.setIdTransferencia(transferencia.getIdTransferencia());
        return transfer;
    }

    public void doGuardarOperacion(OperacionDTO opDTO, TransferenciaDTO transfer, CambioDivisaDTO cambio, SacardineroDTO sacar){
        OperacionEntity op = new OperacionEntity();
        op.setIdoperacion(opDTO.getIdOperacion());
        op.setIdcliente(opDTO.getCliente());
        op.setCuentaByIdcuenta(cuentaRepository.getById(opDTO.getCuenta().getIdcuenta()));
        op.setFecha(opDTO.getFecha());

        List<TransferenciaEntity> transfers = new ArrayList<>();
        List<CambiodivisaEntity> cambios = new ArrayList<>();
        List<SacardineroEntity> sacars = new ArrayList<>();
        if(transfer != null){
            TransferenciaEntity transferencia = transferenciasRepository.getById(transfer.getIdTransferencia());
            transfers.add(transferencia);
        }
        if(cambio != null){
            CambiodivisaEntity cambiodivisa = cambioDivisaRepository.getById(cambio.getIdCambioDivisa());
            cambios.add(cambiodivisa);
        }
        if(sacar != null){
            SacardineroEntity sacardinero = sacarDineroRepository.getById(sacar.getIdSacarDinero());
            sacars.add(sacardinero);
        }

        op.setTransferenciasByIdoperacion(transfers);
        op.setCambiodivisasByIdoperacion(cambios);
        op.setSacardinerosByIdoperacion(sacars);

        operacionesRepository.save(op);

    }

    public void doGuardarCuenta(CuentaDTO cuentaDTO) {
        CuentaEntity cuenta = new CuentaEntity();
        cuenta.setIdcuenta(cuentaDTO.getIdcuenta());
        cuenta.setNumerocuenta(cuentaDTO.getNumerocuenta());
        cuenta.setSaldo(cuentaDTO.getSaldo());
        cuenta.setFechacierre(cuentaDTO.getFechacierre());
        cuenta.setFechaapertura(cuentaDTO.getFechaapertura());
        cuenta.setClienteByIdcliente(clienteRepository.getById(cuentaDTO.getCliente().getIdCliente()));
        cuenta.setTipoestadoByIdestado(tipoEstadoRepository.getById(cuentaDTO.getTipoEstado().getIdTipoestado()));
        cuenta.setOperacionsByIdcuenta(operacionesRepository.buscarPorCuenta(cuentaDTO.getIdcuenta()));
        cuentaRepository.save(cuenta);
    }


}
