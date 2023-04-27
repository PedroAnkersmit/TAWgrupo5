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

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
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
    String doTransfer(Model model, @RequestParam("id") Integer idCuenta){
        CuentaEntity cuentaEmisor = cuentaRepository.findById(idCuenta).orElse(null);
        CuentaEntity cuentaReceptor = new CuentaEntity();
        /*cuentaReceptor.setFechaapertura(new Date(System.currentTimeMillis()));
        cuentaReceptor.setFechacierre(new Date(System.currentTimeMillis()));
        cuentaReceptor.setIdcuenta(0);
        cuentaReceptor.setNumerocuenta("0");
        cuentaReceptor.setSaldo(BigDecimal.ZERO);
        cuentaReceptor.setClienteByIdcliente(null);*/
        List<CuentaEntity> listaCuentas = cuentaRepository.findAll();
        listaCuentas.remove(cuentaEmisor);
        model.addAttribute("idAccount", idCuenta);
        model.addAttribute("accountList", listaCuentas);

        return "clienteElegirCuentaReceptora";
   }
   @PostMapping("/transferView")
    String doExecuteTransfer(Model model, @RequestParam("idCuentaReceptora") Integer idCuentaReceptora,
                             @RequestParam("idAccount") Integer idCuentaEmisora){
        CuentaEntity cuentaReceptora = cuentaRepository.findById(idCuentaReceptora).orElse(null);
        CuentaEntity cuentaEmisora = cuentaRepository.findById(idCuentaEmisora).orElse(null);
        ClienteEntity clienteEmisor = clienteRepository.findById(cuentaEmisora.getClienteByIdcliente().getIdcliente()).orElse(null);

        TransferenciaEntity transferencia = new TransferenciaEntity();

        transferencia.setFechainstruccion(new Date(System.currentTimeMillis()));
        transferencia.setFechaejecucion(new Date(System.currentTimeMillis()));
        transferencia.setCantidad(BigDecimal.ZERO);

        OperacionEntity operacion = new OperacionEntity();
        operacion.setCuentaByIdcuenta(cuentaEmisora);
        operacion.setIdcliente(clienteEmisor.getIdcliente());
        operacion.setTransferenciaByIdoperacion(transferencia);
        //transferenciasRepository.save(transferencia);
        operacionesRepository.save(operacion);



        model.addAttribute("sendAccount", cuentaReceptora);
        model.addAttribute("idAccount", idCuentaEmisora);
        model.addAttribute("transference", transferencia);
        model.addAttribute("operation", operacion);
    return "clienteSeleccionarCantidad";
   }

   @PostMapping("executeTransfer")
    String doExecuteTransfer(Model model, @RequestParam("idReceivingAccount") Integer idCuentaReceptora, @RequestParam("idAccount") Integer idCuentaEmisora,
                             @RequestParam("idOperation") Integer idOperacion, @RequestParam("cantidad") Integer cantidad){
        BigDecimal c = new BigDecimal(cantidad);
        OperacionEntity operacion= operacionesRepository.findById(idOperacion).orElse(null);
        TransferenciaEntity transferencia = operacion.getTransferenciaByIdoperacion();
        transferencia.setCantidad(c);
        transferencia.setFechaejecucion(new Date(System.currentTimeMillis()));
        CuentaEntity cuentaReceptora = cuentaRepository.findById(idCuentaReceptora).orElse(null);
        cuentaReceptora.setSaldo(cuentaReceptora.getSaldo().add(c));
        CuentaEntity cuentaEmisora = cuentaRepository.findById(idCuentaEmisora).orElse(null);
        cuentaEmisora.setSaldo(cuentaEmisora.getSaldo().subtract(c));
        operacion.setTransferenciaByIdoperacion(transferencia);
        //transferenciasRepository.save(transferencia);
        operacionesRepository.save(operacion);
        cuentaRepository.save(cuentaReceptora);
        cuentaRepository.save(cuentaEmisora);
        return "redirect:/clienteHome";
   }
}

