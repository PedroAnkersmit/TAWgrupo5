package com.taw.grupo5.controller;

import com.taw.grupo5.dao.*;
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
    String doFiltrar(Model model,HttpSession httpSession, @ModelAttribute("filtro") FiltroOperaciones filtro){
        ClienteEntity usuario = (ClienteEntity) httpSession.getAttribute("user");
        return doMostrarFiltrado(model,usuario,filtro);
    }
    String doMostrarFiltrado(Model model, ClienteEntity usuario, FiltroOperaciones filtro){
        List<CuentaEntity> cuentasUsuario = cuentaRepository.buscarPorCLiente(usuario.getIdcliente());;
        List<OperacionEntity> operaciones = new ArrayList<>();

        if(filtro == null){
            filtro = new FiltroOperaciones();
            filtro.setCambioDivisa(true);
            filtro.setSacarDinero(true);
            filtro.setTransferencia(true);
            filtro.setCantidad(BigDecimal.ZERO);
        }
        if(filtro.getCantidad() == null){
            filtro.setCantidad(BigDecimal.ZERO);
        }
        if(filtro.isCambioDivisa()){

            operaciones = cambioDivisaRepository.BuscarPorCliente(usuario.getIdcliente());

        }
        if(filtro.isSacarDinero()){

                for(OperacionEntity o :sacarDineroRepository.BuscarPorCliente( usuario.getIdcliente(), filtro.getCantidad())){
                    operaciones.add(o);
            }

        }
        if(filtro.isTransferencia()){

            for(OperacionEntity o :transferenciasRepository.BuscarPorCliente( usuario.getIdcliente(), filtro.getCantidad())){
                operaciones.add(o);
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

        this.operacionesRepository.save(operacion);

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

        transferencia.setOperacionByIdoperacion(op);
        transferencia.setFechainstruccion(new Date(System.currentTimeMillis()));
        transferencia.setFechaejecucion(new Date(System.currentTimeMillis()));
        transferencia.setCantidad(c);


        cuentaReceptora.setSaldo(cuentaReceptora.getSaldo().add(c));

        cuentaEmisora.setSaldo(cuentaEmisora.getSaldo().subtract(c));


        cuentaRepository.save(cuentaReceptora);
        cuentaRepository.save(cuentaEmisora);

        this.transferenciasRepository.save(transferencia);
        return "redirect:/";
   }
}

