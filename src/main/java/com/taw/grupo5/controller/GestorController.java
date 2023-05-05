package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.CuentaRepository;
import com.taw.grupo5.dao.EmpresaRepository;
import com.taw.grupo5.dao.OperacionRepository;
import com.taw.grupo5.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    @Autowired
    CuentaRepository cuentaRepository;

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

        List<ClienteEntity> clienteEntityList = this.clienteRepository.buscarPorEmpresa(idEmpresa);
        model.addAttribute("listadoClientes", clienteEntityList);

        List<Integer> listaIdClientes = new ArrayList<>();
        for(ClienteEntity cliente : clienteEntityList) {
            listaIdClientes.add(cliente.getIdcliente());
        }

        List<OperacionEntity> operacionRepositoryList = this.operacionRepository.buscarPorEmpresa(listaIdClientes);
        model.addAttribute("listaOperaciones", operacionRepositoryList);

        return "gestorEmpresa";
    }

    @GetMapping("listadoDarVistoBuenoAlta")
    public String mostrarListadoDarDeAlta(Model model) {
        List<ClienteEntity> clienteEntityList = this.clienteRepository.listadoClientesDarAlta();
        model.addAttribute("listadoClientesAlta", clienteEntityList);

        return "gestorListadoDarVistoBuenoAlta";
    }

    @GetMapping("darVistoBuenoAlta")
    public String darVistoBuenoAlta(@RequestParam("id") Integer idCuenta) {
        CuentaEntity cuenta = this.cuentaRepository.findById(idCuenta).orElse(null);
        TipoestadoEntity estadoCuenta = new TipoestadoEntity();

        estadoCuenta.setIdtipoestado(2);

        cuenta.setTipoestadoByIdestado(estadoCuenta);
        this.cuentaRepository.save(cuenta);

        return "redirect:/gestor/";
    }

    @GetMapping("listadoInactivos")
    public String mostrarListadoInactivos(Model model) {
        List<ClienteEntity> listadoClientesInactivos = this.clienteRepository.listadoClientesInactivos();
        model.addAttribute("listadoClientesInactivos", listadoClientesInactivos);

        return "gestorListadoInactivos";
    }

    @GetMapping("desactivarCuenta")
    public String desactivarCuenta(@RequestParam("id") Integer idCuenta) {
        CuentaEntity cuenta = this.cuentaRepository.findById(idCuenta).orElse(null);
        TipoestadoEntity estadoCuenta = new TipoestadoEntity();

        estadoCuenta.setIdtipoestado(4);

        cuenta.setTipoestadoByIdestado(estadoCuenta);
        this.cuentaRepository.save(cuenta);

        return "redirect:/gestor/";
    }
}
