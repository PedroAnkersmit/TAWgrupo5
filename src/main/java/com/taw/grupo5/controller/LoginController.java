package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.CuentaRepository;
import com.taw.grupo5.dao.TipoClienteRepository;
import com.taw.grupo5.dao.TipoEstadoRepository;
import com.taw.grupo5.entity.ClienteEntity;
import com.taw.grupo5.entity.CuentaEntity;
import com.taw.grupo5.entity.TipoclienteEntity;
import com.taw.grupo5.entity.TipoestadoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
Created by Pedro Ankersmit Carrión
*/
@Controller
public class LoginController {
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected TipoClienteRepository tipoClienteRepository;
    @Autowired
    protected TipoEstadoRepository tipoEstadoRepository;
    @Autowired
    protected CuentaRepository cuentaRepository;

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
                        @RequestParam("phone") String phone,@RequestParam("account") String nCuenta,
                        @RequestParam("date") String date,
                        Model model, HttpSession httpSession) throws ParseException {
        ClienteEntity cliente = new ClienteEntity();
        CuentaEntity cuenta = new CuentaEntity();
        TipoestadoEntity tipoestadoEntity = tipoEstadoRepository.getById(1);

        SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy");
        long epoch = obj.parse(date).getTime();
        java.sql.Date fechaCierre = new java.sql.Date(epoch);
        cuenta.setFechaapertura(new java.sql.Date(System.currentTimeMillis()));
        cuenta.setFechacierre(fechaCierre);
        cuenta.setSaldo(BigDecimal.ZERO);
        cuenta.setClienteByIdcliente(cliente);
        cuenta.setTipoestadoByIdestado(tipoestadoEntity);
        cuenta.setNumerocuenta(nCuenta);

        TipoclienteEntity tipocliente = tipoClienteRepository.findById(1).orElse(null);

        cliente.setNombre(name);
        cliente.setEmail(mail);
        cliente.setTelefono(phone);
        cliente.setFechainicio(new java.sql.Date(System.currentTimeMillis()));
        cliente.setTipoclienteByIdtipocliente(tipocliente);

        clienteRepository.save(cliente);
        cuentaRepository.save(cuenta);
        return doAutenticar(mail, model, httpSession);
    }
}
