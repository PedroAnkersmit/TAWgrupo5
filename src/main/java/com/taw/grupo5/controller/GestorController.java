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
    OperacionRepository operacionesRepository;

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

    @PostMapping("/filtrar")
    String doFiltrar(Model model,@RequestParam("idCliente") Integer idCliente, @ModelAttribute("filtro") FiltroOperaciones filtro){
        ClienteEntity usuario = clienteRepository.findById(idCliente).orElse(null);
        return doMostrarFiltrado(model,usuario,filtro);
    }

    String doMostrarFiltrado(Model model, ClienteEntity usuario, FiltroOperaciones filtro){
        List<CuentaEntity> cuentasUsuario = cuentaRepository.buscarPorCLiente(usuario.getIdcliente());
        List<OperacionEntity> operaciones = new ArrayList<>();
        if(filtro == null){
            filtro = new FiltroOperaciones(true, true, true);
        }

        if(filtro.isCambioDivisa()&& filtro.isTransferencia() && filtro.isSacarDinero()){
            operaciones = operacionesRepository.buscarTodas(usuario.getIdcliente());
        } else if(filtro.isCambioDivisa() && filtro.isTransferencia()){
            operaciones = operacionesRepository.buscarCambioDivisaTransferencia(usuario.getIdcliente());
        } else if(filtro.isCambioDivisa() && filtro.isSacarDinero()){
            operaciones = operacionesRepository.buscarCambioDivisaSacarDinero(usuario.getIdcliente());
        } else if (filtro.isTransferencia() && filtro.isSacarDinero()) {
            operaciones = operacionesRepository.buscarSacarDineroTransferencia(usuario.getIdcliente());
        } else{
            if(filtro.isCambioDivisa() && !filtro.isTransferencia() && !filtro.isSacarDinero()) {
                operaciones = operacionesRepository.buscarCambioDivisa(usuario.getIdcliente());
            } else if (filtro.isTransferencia() && !filtro.isCambioDivisa() && !filtro.isSacarDinero() ) {
                operaciones = operacionesRepository.buscarTransferencia(usuario.getIdcliente());

            } else if(filtro.isSacarDinero() && !filtro.isTransferencia() && !filtro.isCambioDivisa()) {
                operaciones = operacionesRepository.buscarSacarDinero(usuario.getIdcliente());
            }
        }


        model.addAttribute("user", usuario);
        model.addAttribute("accounts", cuentasUsuario);
        model.addAttribute("operations", operaciones);
        model.addAttribute("filtro", filtro);
        return "clienteHome";
    }

    @GetMapping("cliente")
    public String mostrarDatosCliente(@RequestParam("id") Integer idCliente, Model model) {
        ClienteEntity clienteEntity = this.clienteRepository.findById(idCliente).orElse(null);
        model.addAttribute("cliente", clienteEntity);

        List<OperacionEntity> operacionRepositoryList = this.operacionesRepository.buscarPorCliente(idCliente);
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

        List<OperacionEntity> operacionRepositoryList = this.operacionesRepository.buscarPorEmpresa(listaIdClientes);
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
