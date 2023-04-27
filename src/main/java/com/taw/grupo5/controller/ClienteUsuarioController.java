package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.CuentaRepository;
import com.taw.grupo5.dao.OperacionesRepository;
import com.taw.grupo5.dao.TransferenciasRepository;
import com.taw.grupo5.entity.ClienteEntity;
import com.taw.grupo5.entity.CuentaEntity;
import com.taw.grupo5.entity.OperacionEntity;
import com.taw.grupo5.entity.TransferenciaEntity;
import com.taw.grupo5.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
/*
Created by Pedro Ankersmit Carrión
*/
@Controller
@RequestMapping("/clienteHome")
public class ClienteUsuarioController {
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected CuentaRepository cuentaRepository;
    @Autowired
    protected OperacionesRepository operacionesRepository;
    @Autowired
    protected TransferenciasRepository transferenciasRepository;

    @GetMapping("/")
    String doMostrar(Model model, HttpSession httpSession){
        ClienteEntity usuario = (ClienteEntity) httpSession.getAttribute("user");
        return doMostrarFiltrado(model,usuario,null);
    }
    @PostMapping("/filtrar")
    String doFiltrar(Model model,HttpSession httpSession, @ModelAttribute("filtro") FiltroOperaciones filtro){
        ClienteEntity usuario = (ClienteEntity) httpSession.getAttribute("user");
        return doMostrarFiltrado(model,usuario,filtro);
    }
    String doMostrarFiltrado(Model model, ClienteEntity usuario, FiltroOperaciones filtro){
        List<CuentaEntity> cuentasUsuario = cuentaRepository.buscarPorCLiente(usuario.getIdcliente());;
        List<OperacionEntity> operaciones = operacionesRepository.buscarPorCliente(usuario.getIdcliente());
        if(filtro == null){
            filtro = new FiltroOperaciones();
        }
        if(filtro.getCantidad() == null){
            filtro.setCantidad(BigDecimal.ZERO);
        }
        if(filtro.isCambioDivisa()){

            operaciones = operacionesRepository.buscarCambioDivisa(usuario.getIdcliente());

        } else if(filtro.isSacarDinero()){

                operaciones = operacionesRepository.buscarSacarDinero(filtro.getCantidad(), usuario.getIdcliente());

        } else if(filtro.isTransferencia()){

                operaciones = operacionesRepository.buscarTransferencia(filtro.getCantidad(), usuario.getIdcliente());

        } else{
            operaciones = operacionesRepository.buscarPorCantidad(filtro.getCantidad(), usuario.getIdcliente());
        }


        model.addAttribute("user", usuario);
        model.addAttribute("accounts", cuentasUsuario);
        model.addAttribute("operations", operaciones);
        model.addAttribute("filtro", filtro);
        return "clienteHome";
    }
    @GetMapping("/editar")
    String doEditar(Model model, @RequestParam("id") Integer idcliente){
        ClienteEntity usuario = clienteRepository.findById(idcliente).orElse(null);
        model.addAttribute("user",usuario);
        return "clienteEditar";
    }
    @PostMapping("/guardar")
    String doGuardar(Model model, @ModelAttribute("user") ClienteEntity usuario){
        clienteRepository.save(usuario);
        return "/clienteHome";
    }
    @GetMapping("/transfer")
    String doTransferencia(Model model, @RequestParam("id") Integer idcuenta){
        List<CuentaEntity> listaCuentas = cuentaRepository.findAll();
        CuentaEntity cuentaCliente = cuentaRepository.findById(idcuenta).orElse(null);
        OperacionEntity operacion = new OperacionEntity();
        operacion.setCuentaByIdcuenta(cuentaCliente);
        TransferenciaEntity transferencia = new TransferenciaEntity();
        transferencia.setOperacionByIdoperacion(operacion);
        transferencia.setFechainstruccion(new java.sql.Date(System.currentTimeMillis()));

        model.addAttribute("allAccounts", listaCuentas);
        model.addAttribute("transference", transferencia);
        return "/clienteTransferencia";
    }

    @PostMapping("/ejecutarTransferencia")
    String doConfirmTransfer(Model model, @ModelAttribute("transference") TransferenciaEntity transferencia){
        CuentaEntity cuentaEmisora = cuentaRepository.getById(transferencia.getOperacionByIdoperacion().getCuentaByIdcuenta().getIdcuenta());
        CuentaEntity cuentaObjetivo = cuentaRepository.getById(transferencia.getOperacionByIdoperacion().getCuentaByIdcuenta().getIdcuenta());
        cuentaObjetivo.setSaldo(cuentaObjetivo.getSaldo().add(transferencia.getCantidad()));
        OperacionEntity op =  operacionesRepository.findById(transferencia.getOperacionByIdoperacion().getIdoperacion()).orElse(null);
        transferencia.setFechaejecucion(new java.sql.Date(System.currentTimeMillis()));

        op.setTransferenciaByIdoperacion(transferencia);
        cuentaObjetivo.getOperacionsByIdcuenta().add(op);



        transferenciasRepository.save(transferencia);
        operacionesRepository.save(op);
        cuentaRepository.save(cuentaObjetivo);

        return "";
    }
}
