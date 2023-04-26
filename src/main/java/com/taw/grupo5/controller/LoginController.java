package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.TipoClienteRepository;
import com.taw.grupo5.entity.ClienteEntity;
import com.taw.grupo5.entity.CuentaEntity;
import com.taw.grupo5.entity.TipoclienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/*
Created by Pedro Ankersmit Carrión
*/
@Controller
public class LoginController {
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected TipoClienteRepository tipoClienteRepository;

    @GetMapping("/")
    String doLogin() {
        return "clienteLogin";
    }

    @PostMapping("/")
    String doAutenticar(@RequestParam("mail") String mail,
                        Model model, HttpSession session) {
        String urlTo = "redirect:/clienteHome/";
        ClienteEntity usuario = clienteRepository.buscarCuenta(mail);
        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "/";
        } else {
            session.setAttribute("user", usuario);
        }
        return urlTo;
    }

    @GetMapping("/alta")
    String doAlta() {
        return "clienteAlta";
    }

    @PostMapping("/alta")
    String crearCliente(@RequestParam("mail") String mail, @RequestParam("name") String name,
                        @RequestParam("phone") String phone, Model model, HttpSession httpSession) {
        ClienteEntity cliente = new ClienteEntity();
        CuentaEntity cuenta = new CuentaEntity();
        cuenta.setFechaapertura(new java.sql.Date(System.currentTimeMillis()));
        //cuenta.setFechacierre();

        TipoclienteEntity tipocliente = tipoClienteRepository.findById(1).orElse(null);
        cliente.setNombre(name);
        cliente.setEmail(mail);
        cliente.setTelefono(phone);
        cliente.setFechainicio(new java.sql.Date(System.currentTimeMillis()));
        cliente.setTipoclienteByIdtipocliente(tipocliente);

        clienteRepository.save(cliente);
        return doAutenticar(mail, model, httpSession);
    }
}
