package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.CuentaRepository;
import com.taw.grupo5.entity.ClienteEntity;
import com.taw.grupo5.entity.CuentaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CajeroController {

    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected CuentaRepository cuentaRepository;

    @GetMapping("/cajero/datos")
    public String doMostrar(@RequestParam("idCliente") Integer idCliente, Model model){

        ClienteEntity cliente = clienteRepository.findById(idCliente).orElse(null);

        model.addAttribute("cliente", cliente);

        return "cajeroDatos";
    }



}
