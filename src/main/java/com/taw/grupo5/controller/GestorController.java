package com.taw.grupo5.controller;

import com.taw.grupo5.dao.*;
import com.taw.grupo5.entity.*;
import com.taw.grupo5.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    OperacionesRepository operacionesRepository;

    @GetMapping("/")
    public String mostrarClientesYEmpresas(Model model) {
        List<ClienteEntity> clienteEntityList = this.clienteRepository.findAll();
        model.addAttribute("listaClientes", clienteEntityList);

        List<EmpresaEntity> empresaEntityList = this.empresaRepository.findAll();
        model.addAttribute("listaEmpresas", empresaEntityList);

        return "gestorListar";
    }

    public String mostrarDatosCliente(@RequestParam("id") Integer idCliente, Model model) {
        ClienteEntity clienteEntity = this.clienteRepository.findById(idCliente).orElse(null);
        model.addAttribute("cliente", clienteEntity);

        return doMostrarFiltrado(model, clienteEntity, null);
    }
    @GetMapping("cliente")
    public String mostrarDatosClienteFiltro(@RequestParam("id") Integer idCliente, Model model, @ModelAttribute("filtro") FiltroOperaciones filtro) {
        ClienteEntity clienteEntity = this.clienteRepository.findById(idCliente).orElse(null);
        model.addAttribute("cliente", clienteEntity);

        return doMostrarFiltrado(model, clienteEntity, filtro);
    }

    String doMostrarFiltrado(Model model, ClienteEntity cliente, FiltroOperaciones filtro){
        List<CuentaEntity> cuentasCliente = cuentaRepository.buscarPorCLiente(cliente.getIdcliente());
        List<OperacionEntity> operaciones = new ArrayList<>();
        if(filtro == null){
            filtro = new FiltroOperaciones(true, true, true);
        }

        if(filtro.isCambioDivisa()&& filtro.isTransferencia() && filtro.isSacarDinero()){
            operaciones = operacionesRepository.buscarTodas(cliente.getIdcliente());
        } else if(filtro.isCambioDivisa() && filtro.isTransferencia()){
            operaciones = operacionesRepository.buscarCambioDivisaTransferencia(cliente.getIdcliente());
        } else if(filtro.isCambioDivisa() && filtro.isSacarDinero()){
            operaciones = operacionesRepository.buscarCambioDivisaSacarDinero(cliente.getIdcliente());
        } else if (filtro.isTransferencia() && filtro.isSacarDinero()) {
            operaciones = operacionesRepository.buscarSacarDineroTransferencia(cliente.getIdcliente());
        } else{
            if(filtro.isCambioDivisa() && !filtro.isTransferencia() && !filtro.isSacarDinero()) {
                operaciones = operacionesRepository.buscarCambioDivisa(cliente.getIdcliente());
            } else if (filtro.isTransferencia() && !filtro.isCambioDivisa() && !filtro.isSacarDinero() ) {
                operaciones = operacionesRepository.buscarTransferencia(cliente.getIdcliente());

            } else if(filtro.isSacarDinero() && !filtro.isTransferencia() && !filtro.isCambioDivisa()) {
                operaciones = operacionesRepository.buscarSacarDinero(cliente.getIdcliente());
            }
        }

        model.addAttribute("cliente", cliente);
        model.addAttribute("cuentasCliente", cuentasCliente);
        model.addAttribute("listaOperaciones", operaciones);
        model.addAttribute("filtro", filtro);
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

    @GetMapping("listadoSospechosas")
    public String mostrarListadoSospechosas(Model model) {
        List<CuentaEntity> listadoCuentasSospechosas = this.cuentaRepository.listadoCuentasSospechosas();
        model.addAttribute("listadoCuentasSospechosas", listadoCuentasSospechosas);

        return "gestorListadoSospechosas";
    }
}
