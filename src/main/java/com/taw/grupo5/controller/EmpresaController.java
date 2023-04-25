package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.CuentaRepository;
import com.taw.grupo5.dao.EmpresaRepository;
import com.taw.grupo5.dao.OperacionRepository;
import com.taw.grupo5.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected OperacionRepository operacionRepository;

    @GetMapping("")
    public String empresaInicio(Model model)
    {
        return "empresaInicio";
    }

    @GetMapping("/registro")
    public String empresaRegistro(Model model)
    {
        EmpresaRegistro empresaRegistro = new EmpresaRegistro();
        model.addAttribute("empresaregistro", empresaRegistro);

        return "empresaRegistro";
    }

    public String generateBankNumber() {
        Random random = new Random();
        StringBuilder bankNumberBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            bankNumberBuilder.append(random.nextInt(10));
        }

        return bankNumberBuilder.toString();
    }

    @PostMapping("/registro/guardar")
    public String empresaRegistroGuardar(@ModelAttribute("empresaregistro") EmpresaRegistro empresaRegistro, Model model)
    {
        EmpresaEntity empresa = empresaRegistro.getEmpresa();
        ClienteEntity cliente = empresaRegistro.getCliente();
        CuentaEntity cuenta = empresaRegistro.getCuenta();

        LocalDate today = LocalDate.now();
        TipoclienteEntity tipoClienteSocioNuevo = new TipoclienteEntity();
        TipoestadoEntity estadoCuentaNueva = new TipoestadoEntity();

        this.empresaRepository.save(empresa);

        cliente.setFechainicio(Date.valueOf(today));
        tipoClienteSocioNuevo.setIdtipocliente(2);
        cliente.setTipoclienteByIdtipocliente(tipoClienteSocioNuevo);
        cliente.setEmpresaByIdempresa(empresa);
        this.clienteRepository.save(cliente);

        cuenta.setClienteByIdcliente(cliente);
        cuenta.setNumerocuenta(generateBankNumber());

        estadoCuentaNueva.setIdtipoestado(1);

        cuenta.setTipoestadoByIdestado(estadoCuentaNueva);
        cuenta.setSaldo(null);
        cuenta.setFechaapertura(Date.valueOf(today));
        this.cuentaRepository.save(cuenta);

        return "redirect:/";
    }

    @GetMapping("/dardealta")
    public String darDeAlta(@RequestParam("id") Integer idEmpresa, Model model)
    {
        EmpresaEntity empresa = this.empresaRepository.findById(idEmpresa).orElse(null);
        PersonaDeAlta personaDeAlta = new PersonaDeAlta();

        model.addAttribute("empresaAlta", empresa);
        model.addAttribute("personaDeAlta", personaDeAlta);

        return "empresaDarDeAlta";
    }

    @PostMapping("/dardealta/guardar")
    public String guardarAlta(@ModelAttribute("personaDeAlta") PersonaDeAlta personaDeAlta, Model model)
    {

        ClienteEntity cliente = personaDeAlta.getCliente();
        CuentaEntity cuenta = personaDeAlta.getCuenta();

        LocalDate today = LocalDate.now();
        TipoestadoEntity estadoCuentaNueva = new TipoestadoEntity();

        cliente.setFechainicio(Date.valueOf(today));
        this.clienteRepository.save(cliente);

        cuenta.setClienteByIdcliente(cliente);
        cuenta.setNumerocuenta(generateBankNumber());

        estadoCuentaNueva.setIdtipoestado(1);

        cuenta.setTipoestadoByIdestado(estadoCuentaNueva);
        cuenta.setSaldo(null);
        cuenta.setFechaapertura(Date.valueOf(today));
        this.cuentaRepository.save(cuenta);

        return "redirect:/";
    }

    @GetMapping("/portal")
    public String portalEmpleado(@RequestParam("id") Integer idCliente, Model model)
    {
        ClienteEntity cliente = this.clienteRepository.findById(idCliente).orElse(null);
        List<ClienteEntity> listaClientes = this.clienteRepository.buscarPorEmpresa(cliente.getEmpresaByIdempresa().getIdempresa());
        List<OperacionEntity> listaOperaciones = this.operacionRepository.findAll();

        model.addAttribute("clientePortal", cliente);
        model.addAttribute("listaClientes", listaClientes);
        model.addAttribute("listaOperaciones", listaOperaciones);

        return "empresaPortalEmpleado";
    }

    @GetMapping("/editarcliente")
    public String editarCliente(@RequestParam("id") Integer idCliente, Model model)
    {
        ClienteEntity cliente = this.clienteRepository.findById(idCliente).orElse(null);
        model.addAttribute("clienteAEditar", cliente);

        return "empresaEditarCliente";
    }

    @PostMapping("/editarcliente/guardar")
    public String guardarEditarCliente(@ModelAttribute("clienteAEditar") ClienteEntity cliente, Model model)
    {
        this.clienteRepository.save(cliente);

        return "redirect:/";
    }

    @GetMapping("/editarempresa")
    public String editarEmpresa(@RequestParam("id") Integer idEmpresa, Model model)
    {
        EmpresaEntity empresa = this.empresaRepository.findById(idEmpresa).orElse(null);
        model.addAttribute("empresaAEditar", empresa);

        return "empresaEditarEmpresa";
    }

    @PostMapping("/editarempresa/guardar")
    public String guardarEditarEmpresa(@ModelAttribute("empresaAEditar") EmpresaEntity empresa, Model model)
    {
        this.empresaRepository.save(empresa);

        return "redirect:/";
    }

    @GetMapping("/bloquear")
    public String bloquearCliente(@RequestParam("id") Integer idCliente, Model model)
    {
        ClienteEntity cliente = this.clienteRepository.findById(idCliente).orElse(null);
        CuentaEntity cuenta = this.cuentaRepository.findById(cliente.getCuentasByIdcliente().get(0).getIdcuenta()).orElse(null);
        TipoestadoEntity estadoCuenta = new TipoestadoEntity();

        estadoCuenta.setIdtipoestado(4);

        cuenta.setTipoestadoByIdestado(estadoCuenta);

        this.cuentaRepository.save(cuenta);

        return "redirect:/";
    }

    @GetMapping("/solicitarActivacion")
    public String solicitarActivacion(@RequestParam("id") Integer idCliente, Model model)
    {
        ClienteEntity cliente = this.clienteRepository.findById(idCliente).orElse(null);
        CuentaEntity cuenta = this.cuentaRepository.findById(cliente.getCuentasByIdcliente().get(0).getIdcuenta()).orElse(null);
        TipoestadoEntity estadoCuenta = new TipoestadoEntity();

        estadoCuenta.setIdtipoestado(1);

        cuenta.setTipoestadoByIdestado(estadoCuenta);

        this.cuentaRepository.save(cuenta);

        return "redirect:/";
    }
}
