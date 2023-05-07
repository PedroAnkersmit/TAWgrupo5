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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Jesús Ariza Pomares
 */

@Controller
@RequestMapping("/empresa")
public class LoginEmpresaController {
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected TipoClienteRepository tipoClienteRepository;
    @Autowired
    protected TipoEstadoRepository tipoEstadoRepository;
    @Autowired
    protected CuentaRepository cuentaRepository;

    @GetMapping("/login")
    String loginCuentaEmpresa() {
        return "clienteEmpresaLogin";
    }

    @PostMapping("/login")
    String loggearCuentaEmpresa(@RequestParam("mail") String mail, Model model, HttpSession session){
        return autenticarCuentaEmpresa(mail, model, session);
    }
    String autenticarCuentaEmpresa(String mail, Model model, HttpSession session) {

        ClienteEntity clienteEmpresa = clienteRepository.autenticar(mail);

        String urlTo = "redirect:/empresa/portal";

        if (clienteEmpresa == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "/empresa/login";
        } else
            session.setAttribute("clienteEmpresa", clienteEmpresa);

        return urlTo;
    }
}