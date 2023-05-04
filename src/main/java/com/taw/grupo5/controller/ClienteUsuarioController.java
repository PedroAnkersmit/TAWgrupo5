package com.taw.grupo5.controller;

import com.taw.grupo5.dao.*;
import com.taw.grupo5.dto.*;
import com.taw.grupo5.service.ClienteUsuarioService;
import com.taw.grupo5.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/*
Created by Pedro Ankersmit Carrión
*/
@Controller
@RequestMapping("/clienteHome")
public class ClienteUsuarioController {

    @Autowired
    protected ClienteUsuarioService clienteUsuarioService;

    @GetMapping("/")
    String doMostrar(Model model, HttpSession httpSession){
        ClienteDTO usuario = (ClienteDTO) httpSession.getAttribute("user");
        return doMostrarFiltrado(model,usuario,null);
    }
    @PostMapping("/filtrar")
    String doFiltrar(Model model,@RequestParam("idCliente") Integer idCliente, @ModelAttribute("filtro") FiltroOperaciones filtro){
        ClienteDTO usuario = clienteUsuarioService.doBuscarUsuario(idCliente);
        return doMostrarFiltrado(model,usuario,filtro);
    }
    String doMostrarFiltrado(Model model, ClienteDTO usuario, FiltroOperaciones filtro){
        List<CuentaDTO> cuentasUsuario = clienteUsuarioService.doBuscarCuentasPorUsuario(usuario.getIdCliente());;

        if(filtro == null){
            filtro = new FiltroOperaciones(true, true, true);
        }
        List<TransferenciaDTO> transferenciaDTOS = clienteUsuarioService.doFiltrarTransferencias(filtro,usuario);
        List<CambioDivisaDTO> cambioDivisaDTOS = clienteUsuarioService.doFiltrarCambios(filtro,usuario);
        List<SacardineroDTO> sacardineroDTOS = clienteUsuarioService.doFiltrarExtracciones(filtro,usuario);


        model.addAttribute("user", usuario);
        model.addAttribute("accounts", cuentasUsuario);
        model.addAttribute("transfer", transferenciaDTOS);
        model.addAttribute("change", cambioDivisaDTOS);
        model.addAttribute("extract", sacardineroDTOS);
        model.addAttribute("filtro", filtro);
        return "clienteHome";
    }
    @GetMapping("/editar")
    String doEditar(Model model, @RequestParam("id") Integer idcliente){
        ClienteDTO usuario = clienteUsuarioService.doBuscarUsuario(idcliente);
        model.addAttribute("user",usuario);
        return "clienteEditar";
    }
    @PostMapping("/guardar")
    String doGuardar(Model model, @ModelAttribute("user") ClienteDTO usuario){
        clienteUsuarioService.doGuardarUsuario(usuario);
        return "/clienteHome";
    }

   @GetMapping("/transfer")
    String doTransfer(Model model, @RequestParam("id") Integer idCuenta){
        CuentaDTO cuentaEmisor = clienteUsuarioService.doBuscarCuenta(idCuenta);
        List<CuentaDTO> listaCuentas = clienteUsuarioService.doCogerCuentas();
        listaCuentas.remove(cuentaEmisor);
        model.addAttribute("idAccount", idCuenta);
        model.addAttribute("accountList", listaCuentas);

        return "clienteElegirCuentaReceptora";
   }
   @PostMapping("/transferView")
    String doViewTransfer(Model model, @RequestParam("idCuentaReceptora") Integer idCuentaReceptora,
                             @RequestParam("idAccount") Integer idCuentaEmisora){
        CuentaDTO cuentaReceptora = clienteUsuarioService.doBuscarCuenta(idCuentaReceptora);
        CuentaDTO cuentaEmisora = clienteUsuarioService.doBuscarCuenta(idCuentaEmisora);

        OperacionDTO operacion= clienteUsuarioService.doCrearOperacion(cuentaEmisora);

        model.addAttribute("idOperacion", operacion.getIdOperacion());
        model.addAttribute("receiveAccount", cuentaReceptora);
        model.addAttribute("sendAccount", cuentaEmisora);
    return "clienteSeleccionarCantidad";
   }

   @PostMapping("/executeTransfer")
    String doExecuteTransfer(Model model, @RequestParam("idReceivingAccount") Integer idCuentaReceptora,
                             @RequestParam("idAccount") Integer idCuentaEmisora,
                             @RequestParam("cantidad") Integer cantidad, @RequestParam("idOperation") Integer idOp) {
        BigDecimal c = new BigDecimal(cantidad);
        CuentaDTO cuentaReceptora = clienteUsuarioService.doBuscarCuenta(idCuentaReceptora);
        CuentaDTO cuentaEmisora = clienteUsuarioService.doBuscarCuenta(idCuentaEmisora);
        OperacionDTO op = clienteUsuarioService.doBuscarOperacion(idOp);

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setFechaInstruccion(new Date(System.currentTimeMillis()));
        transferencia.setFechaEjecucion(new Date(System.currentTimeMillis()));
        transferencia.setCantidad(c);
        transferencia.setOperacion(op.getIdOperacion());

        cuentaReceptora.setSaldo(cuentaReceptora.getSaldo().add(c));

        cuentaEmisora.setSaldo(cuentaEmisora.getSaldo().subtract(c));

        transferencia = clienteUsuarioService.doGuardarTransferencia(transferencia);
        clienteUsuarioService.doGuardarOperacion(op,transferencia,null,null);
        clienteUsuarioService.doGuardarCuenta(cuentaReceptora);
        clienteUsuarioService.doGuardarCuenta(cuentaEmisora);
        return doMostrarFiltrado(model, cuentaEmisora.getCliente(), null);
   }
    @GetMapping("/cambio")
    String doCambiar(Model model, @RequestParam("id") Integer idCuenta){

        CuentaDTO cuenta = clienteUsuarioService.doBuscarCuenta(idCuenta);

        OperacionDTO operacion = clienteUsuarioService.doCrearOperacion(cuenta);

        model.addAttribute("operationId", operacion.getIdOperacion());
        model.addAttribute("accountId", idCuenta);
        return "clienteCambioDivisa";
    }
    @PostMapping("/cambio")
    String doExecuteCambiar(Model model, @RequestParam("idCuenta") Integer idCuenta,
                            @RequestParam("cantidad") Integer cantidad,
                            @RequestParam("idOperacion") Integer operacion){

        CuentaDTO cuenta = clienteUsuarioService.doBuscarCuenta(idCuenta);
        OperacionDTO op = clienteUsuarioService.doBuscarOperacion(operacion);
        BigDecimal c = new BigDecimal(cantidad).multiply(new BigDecimal(0.91)).setScale(2, RoundingMode.HALF_DOWN);
        CambioDivisaDTO cambiodivisa = new CambioDivisaDTO();
        cambiodivisa.setOperacion(op);
        cambiodivisa.setMonedaventa("dolar");
        cambiodivisa.setMonedacompra("euro");
        cambiodivisa.setCantidadventa(cantidad.toString());
        Double cantidadAux = c.doubleValue();
        cambiodivisa.setCantidadcompra(cantidadAux.toString());
        cambiodivisa.setComision("0");

        cuenta.setSaldo(cuenta.getSaldo().add(c));

        cambiodivisa = clienteUsuarioService.doGuardarCambioDivisa(cambiodivisa);
        clienteUsuarioService.doGuardarOperacion(op, null, cambiodivisa, null);
        clienteUsuarioService.doGuardarCuenta(cuenta);

        return doMostrarFiltrado(model, cuenta.getCliente(), null);
    }
}

