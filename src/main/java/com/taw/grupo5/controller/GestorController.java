package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.EmpresaRepository;
import com.taw.grupo5.dao.OperacionRepository;
import com.taw.grupo5.entity.ClienteEntity;
import com.taw.grupo5.entity.EmpresaEntity;
import com.taw.grupo5.entity.OperacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/gestor")
public class GestorController {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    OperacionRepository operacionRepository;

    @GetMapping("/")
    public String mostrarClientesYEmpresas(Model model) {
        List<ClienteEntity> clienteEntityList = this.clienteRepository.findAll();
        model.addAttribute("listaClientes", clienteEntityList);

        List<EmpresaEntity> empresaEntityList = this.empresaRepository.findAll();
        model.addAttribute("listaEmpresas", empresaEntityList);

        return "gestorListar";
    }

    @GetMapping("cliente")
    public String mostrarDatosCliente(@RequestParam("id") Integer idCliente, Model model) {
        ClienteEntity clienteEntity = this.clienteRepository.findById(idCliente).orElse(null);
        model.addAttribute("cliente", clienteEntity);

        List<OperacionEntity> operacionRepositoryList = this.operacionRepository.buscarPorCliente(idCliente);
        model.addAttribute("listaOperaciones", operacionRepositoryList);

        return "gestorCliente";
    }

    @GetMapping("empresa")
    public String mostrarDatosEmpresa(@RequestParam("id") Integer idEmpresa, Model model) {
        EmpresaEntity empresaEntity = this.empresaRepository.findById(idEmpresa).orElse(null);
        model.addAttribute("empresa", empresaEntity);

        return "gestorEmpresa";
    }
}
