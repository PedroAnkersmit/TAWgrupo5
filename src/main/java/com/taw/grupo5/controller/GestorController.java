package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gestor")
public class GestorController {
    @Autowired
    ClienteRepository clienteRepository;

    public String mostrarClientesYEmpresas(Model model) {
        List<ClienteEntity> clienteEntityList = this.clienteRepository.findAll();
        model.addAttribute("listaClientes", clienteEntityList);

        return "listadoClientesYEmpresas";
    }
}
