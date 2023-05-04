package com.taw.grupo5.controller;


import com.taw.grupo5.dto.ClienteDTO;
import com.taw.grupo5.dto.CuentaDTO;
import com.taw.grupo5.dto.TipoclienteDTO;
import com.taw.grupo5.dto.TipoestadoDTO;

import com.taw.grupo5.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
Created by Pedro Ankersmit Carrión
*/
@Controller
public class LoginController {
    @Autowired
    protected LoginService loginService;

    @GetMapping("/")
    String doLogin() {
        return "clienteLogin";
    }

    @PostMapping("/")
    String doLoggear(@RequestParam("mail") String mail,
                     Model model, HttpSession session){
        return doAutenticar(mail, model, session);
    }
    String doAutenticar( String mail,
                        Model model, HttpSession session) {
        String urlTo = "redirect:/clienteHome/";
        ClienteDTO usuario = loginService.doBuscarUsuario(mail);
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
                        @RequestParam("phone") String phone,@RequestParam("account") String nCuenta,
                        @RequestParam("date") String date,
                        Model model, HttpSession httpSession) throws ParseException {
        ClienteDTO cliente = new ClienteDTO();
        CuentaDTO cuenta = new CuentaDTO();
        TipoestadoDTO tipoestadoDTO = loginService.doBuscarTipoEstado(1);

        SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy");
        long epoch = obj.parse(date).getTime();
        java.sql.Date fechaCierre = new java.sql.Date(epoch);
        cuenta.setFechaapertura(new java.sql.Date(System.currentTimeMillis()));
        cuenta.setFechacierre(fechaCierre);
        cuenta.setSaldo(BigDecimal.ZERO);
        cuenta.setCliente(cliente);
        cuenta.setTipoEstado(tipoestadoDTO);
        cuenta.setNumerocuenta(nCuenta);

        TipoclienteDTO tipocliente = loginService.doBuscarTipoCliente(1);

        cliente.setNombre(name);
        cliente.setEmail(mail);
        cliente.setTelefono(phone);
        cliente.setFechainicio(new java.sql.Date(System.currentTimeMillis()));
        cliente.setTipoCliente(tipocliente);

        loginService.GuardarClienteYCuenta(cliente,cuenta);
        return doAutenticar(mail, model, httpSession);
    }
}
