package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.CuentaRepository;
import com.taw.grupo5.entity.ClienteEntity;
import com.taw.grupo5.entity.CuentaEntity;
import com.taw.grupo5.entity.OperacionEntity;
import com.taw.grupo5.entity.TransferenciaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/")
    String doMostrar(Model model, HttpSession httpSession){
        ClienteEntity usuario = (ClienteEntity) httpSession.getAttribute("user");
        List<CuentaEntity> cuentasUsuario = cuentaRepository.buscarPorCLiente(usuario.getIdcliente());
        model.addAttribute("user", usuario);
        model.addAttribute("accounts", cuentasUsuario);
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

        model.addAttribute("allAccounts", listaCuentas);
        model.addAttribute("transference", transferencia);
        return "/clienteTransferencia";
    }
}
