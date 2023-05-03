package com.taw.grupo5.controller;

import com.taw.grupo5.dao.*;
import com.taw.grupo5.entity.*;
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

    @GetMapping("/")
    String doMostrar(Model model, HttpSession httpSession){
        ClienteEntity usuario = (ClienteEntity) httpSession.getAttribute("user");
        return doMostrarFiltrado(model,usuario,null);
    }
    @PostMapping("/filtrar")
    String doFiltrar(Model model,@RequestParam("idCliente") Integer idCliente, @ModelAttribute("filtro") FiltroOperaciones filtro){
        ClienteEntity usuario = clienteRepository.findById(idCliente).orElse(null);
        return doMostrarFiltrado(model,usuario,filtro);
    }
    String doMostrarFiltrado(Model model, ClienteEntity usuario, FiltroOperaciones filtro){
        List<CuentaEntity> cuentasUsuario = cuentaRepository.buscarPorCLiente(usuario.getIdcliente());;
        List<OperacionEntity> operaciones = new ArrayList<>();

        if(filtro != null && filtro.getCantidad() == null){
            filtro.setCantidad(BigDecimal.ZERO);
        }
        if(filtro == null){
            filtro = new FiltroOperaciones();
            filtro.setCambioDivisa(true);
            filtro.setSacarDinero(true);
            filtro.setTransferencia(true);
            filtro.setCantidad(BigDecimal.ZERO);
        }
            if (filtro.isCambioDivisa()) {

                for (OperacionEntity o : operacionesRepository.buscarCambioDivisa(usuario.getIdcliente())) {
                    if (!operaciones.contains(o)) {
                        operaciones.add(o);
                    }
                    }

            } else{
                for (OperacionEntity o : operacionesRepository.buscarCambioDivisa(usuario.getIdcliente())) {
                    if (!operaciones.contains(o)) {
                        operaciones.remove(o);
                    }
                }
            }
            if (filtro.isSacarDinero()) {
                for (OperacionEntity o : operacionesRepository.buscarSacarDinero(filtro.getCantidad(), usuario.getIdcliente())) {
                    if (!operaciones.contains(o)) {
                        operaciones.add(o);
                    }
                }
            } else{
                for (OperacionEntity o : operacionesRepository.buscarSacarDinero(filtro.getCantidad(), usuario.getIdcliente())) {
                    if (!operaciones.contains(o)) {
                        operaciones.remove(o);
                    }
                }
            }
            if (filtro.isTransferencia()) {

                for (OperacionEntity o : operacionesRepository.buscarTransferencia( filtro.getCantidad(), usuario.getIdcliente())) {
                    if (!operaciones.contains(o)) {
                        operaciones.add(o);
                    }
                }

            } else {
                for (OperacionEntity o : operacionesRepository.buscarTransferencia( filtro.getCantidad(), usuario.getIdcliente())) {
                    if (!operaciones.contains(o)) {
                        operaciones.remove(o);
                    }
                }
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
    String doTransfer(Model model, @RequestParam("id") Integer idCuenta){
        CuentaEntity cuentaEmisor = cuentaRepository.findById(idCuenta).orElse(null);
        List<CuentaEntity> listaCuentas = cuentaRepository.findAll();
        listaCuentas.remove(cuentaEmisor);
        model.addAttribute("idAccount", idCuenta);
        model.addAttribute("accountList", listaCuentas);

        return "clienteElegirCuentaReceptora";
   }
   @PostMapping("/transferView")
    String doViewTransfer(Model model, @RequestParam("idCuentaReceptora") Integer idCuentaReceptora,
                             @RequestParam("idAccount") Integer idCuentaEmisora){
        CuentaEntity cuentaReceptora = cuentaRepository.findById(idCuentaReceptora).orElse(null);
        CuentaEntity cuentaEmisora = cuentaRepository.findById(idCuentaEmisora).orElse(null);

        OperacionEntity operacion = new OperacionEntity();

        operacion.setIdcliente(cuentaEmisora.getClienteByIdcliente().getIdcliente());
        operacion.setCuentaByIdcuenta(cuentaEmisora);
        operacion.setFecha(new Date(System.currentTimeMillis()));
        operacionesRepository.save(operacion);

        model.addAttribute("idOperacion", operacion.getIdoperacion());
        model.addAttribute("receiveAccount", cuentaReceptora);
        model.addAttribute("sendAccount", cuentaEmisora);
    return "clienteSeleccionarCantidad";
   }

   @PostMapping("/executeTransfer")
    String doExecuteTransfer(Model model, @RequestParam("idReceivingAccount") Integer idCuentaReceptora,
                             @RequestParam("idAccount") Integer idCuentaEmisora,
                             @RequestParam("cantidad") Integer cantidad, @RequestParam("idOperation") Integer idOp) {
        BigDecimal c = new BigDecimal(cantidad);
        CuentaEntity cuentaReceptora = cuentaRepository.findById(idCuentaReceptora).orElse(null);
        CuentaEntity cuentaEmisora = cuentaRepository.findById(idCuentaEmisora).orElse(null);
        OperacionEntity op = operacionesRepository.findById(idOp).orElse(null);

        TransferenciaEntity transferencia = new TransferenciaEntity();
        operacionesRepository.save(op);

        transferencia.setFechainstruccion(new Date(System.currentTimeMillis()));
        transferencia.setFechaejecucion(new Date(System.currentTimeMillis()));
        transferencia.setCantidad(c);

        cuentaReceptora.setSaldo(cuentaReceptora.getSaldo().add(c));

        cuentaEmisora.setSaldo(cuentaEmisora.getSaldo().subtract(c));

        transferenciasRepository.save(transferencia);
        op.setTransferenciaByIdoperacion(transferencia);
        op.setCambiodivisaByIdoperacion(null);
        op.setSacardineroByIdoperacion(null);
        operacionesRepository.save(op);
        cuentaRepository.save(cuentaReceptora);
        cuentaRepository.save(cuentaEmisora);
        return doMostrarFiltrado(model, cuentaEmisora.getClienteByIdcliente(), null);
   }
    @GetMapping("/cambio")
    String doCambiar(Model model, @RequestParam("id") Integer idCuenta){

        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);

        OperacionEntity operacion = new OperacionEntity();


        operacion.setIdcliente(cuenta.getClienteByIdcliente().getIdcliente());
        operacion.setCuentaByIdcuenta(cuenta);
        operacion.setFecha(new Date(System.currentTimeMillis()));

        operacionesRepository.save(operacion);

        model.addAttribute("operationId", operacion.getIdoperacion());
        model.addAttribute("accountId", idCuenta);
        return "clienteCambioDivisa";
    }
    @PostMapping("/cambio")
    String doExecuteCambiar(Model model, @RequestParam("idCuenta") Integer idCuenta,
                            @RequestParam("cantidad") Integer cantidad,
                            @RequestParam("idOperacion") Integer operacion){

        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);
        OperacionEntity op = operacionesRepository.findById(operacion).orElse(null);

        BigDecimal c = new BigDecimal(cantidad).multiply(new BigDecimal(0.91)).setScale(2, RoundingMode.HALF_DOWN);

        CambiodivisaEntity cambiodivisa = new CambiodivisaEntity();
        operacionesRepository.save(op);

        cambiodivisa.setMonedaventa("dolar");
        cambiodivisa.setMonedacompra("euro");
        cambiodivisa.setCantidadventa(cantidad.toString());
        Double cantidadAux = c.doubleValue();
        cambiodivisa.setCantidadcompra(cantidadAux.toString());
        cambiodivisa.setComision("0");

        cuenta.setSaldo(cuenta.getSaldo().add(c));

        cambioDivisaRepository.save(cambiodivisa);
        op.setCambiodivisaByIdoperacion(cambiodivisa);
        op.setTransferenciaByIdoperacion(null);
        op.setSacardineroByIdoperacion(null);
        operacionesRepository.save(op);

        cuentaRepository.save(cuenta);

        return doMostrarFiltrado(model, cuenta.getClienteByIdcliente(), null);
    }
}

