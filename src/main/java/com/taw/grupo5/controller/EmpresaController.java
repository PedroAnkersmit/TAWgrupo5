package com.taw.grupo5.controller;

import com.taw.grupo5.dao.*;
import com.taw.grupo5.entity.*;
import com.taw.grupo5.ui.FiltroOperacionesEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @Autowired
    protected TransferenciaRepository transferenciaRepository;

    @Autowired
    protected CambiodivisaRepository cambiodivisaRepository;

    @Autowired
    protected TipoClienteRepository tipoclienteRepository;

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

        List<TipoclienteEntity> tipoclienteEntityList = this.tipoclienteRepository.findAll();
        tipoclienteEntityList.remove(0);

        model.addAttribute("empresaAlta", empresa);
        model.addAttribute("personaDeAlta", personaDeAlta);
        model.addAttribute("tiposcliente", tipoclienteEntityList);

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

        List<Integer> lista = new ArrayList<>();

        for(ClienteEntity c : listaClientes)
        {
            lista.add(c.getIdcliente());
        }

        List<OperacionEntity> listaOperaciones = this.operacionRepository.buscarPorEmpresa(lista);

        model.addAttribute("clientePortal", cliente);
        model.addAttribute("listaClientes", listaClientes);
        model.addAttribute("listaOperaciones", listaOperaciones);

        return "empresaPortalEmpleado";
    }

    @GetMapping("/portal/filtrar")
    public String portalEmpleadoFiltrado(@RequestParam("id") Integer idCliente, Model model, FiltroOperacionesEmpresa)
    {
        FiltroOperacionesEmpresa filtro;
        ClienteEntity cliente = this.clienteRepository.findById(idCliente).orElse(null);
        List<ClienteEntity> listaClientes = this.clienteRepository.buscarPorEmpresa(cliente.getEmpresaByIdempresa().getIdempresa());

        List<Integer> lista = new ArrayList<>();

        for(ClienteEntity c : listaClientes)
        {
            lista.add(c.getIdcliente());
        }

        List<OperacionEntity> listaOperaciones = this.operacionRepository.buscarPorEmpresa(lista);
        List<OperacionEntity> listaOperacionesFiltrada = new ArrayList<>();

        for(OperacionEntity ope : listaOperaciones)
        {
            if(filtro.getFechaMinima() <= ope.getFecha())
            {

            }
        }

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
    public String bloquearCliente(@RequestParam("id") Integer idCuenta, Model model)
    {
        CuentaEntity cuenta = this.cuentaRepository.findById(idCuenta).orElse(null);
        TipoestadoEntity estadoCuenta = new TipoestadoEntity();

        estadoCuenta.setIdtipoestado(4);

        cuenta.setTipoestadoByIdestado(estadoCuenta);

        this.cuentaRepository.save(cuenta);

        return "redirect:/";
    }

    @GetMapping("/solicitarActivacion")
    public String solicitarActivacion(@RequestParam("id") Integer idCuenta, Model model)
    {
        CuentaEntity cuenta = this.cuentaRepository.findById(idCuenta).orElse(null);
        TipoestadoEntity estadoCuenta = new TipoestadoEntity();

        estadoCuenta.setIdtipoestado(1);

        cuenta.setTipoestadoByIdestado(estadoCuenta);

        this.cuentaRepository.save(cuenta);

        return "redirect:/";
    }

    @GetMapping("/transferencia")
    public String transferenciaEmpresa(@RequestParam("id") Integer idCuenta, Model model)
    {
        LocalDate today = LocalDate.now();

        CuentaEntity cuenta = this.cuentaRepository.findById(idCuenta).orElse(null);
        ClienteEntity cliente = cuenta.getClienteByIdcliente();
        TransferenciaEntity transferencia = new TransferenciaEntity();

        OperacionEntity operacion = new OperacionEntity();
        operacion.setIdcliente(cliente.getIdcliente());
        operacion.setCuentaByIdcuenta(cuenta);
        operacion.setFecha(Date.valueOf(today));

        this.operacionRepository.save(operacion);

        transferencia.setOperacionByIdoperacion(operacion);

        model.addAttribute("clienteTransferencia", cliente);
        model.addAttribute("cuentaTransferencia", cuenta);
        model.addAttribute("transferencia", transferencia);

        return "empresaTransferencia";
    }

    @PostMapping("/transferencia/enviar")
    public String tramitarTransferenciaEmpresa(@ModelAttribute("transferencia") TransferenciaEntity transferencia, Model model)
    {
        LocalDate today = LocalDate.now();

        BigDecimal balanceOriginal = transferencia.getOperacionByIdoperacion().getCuentaByIdcuenta().getSaldo();

        if(balanceOriginal == null)
            balanceOriginal = BigDecimal.valueOf(0);

        BigDecimal valorARestar = transferencia.getCantidad();

        transferencia.setFechainstruccion(Date.valueOf(today));

        CuentaEntity cuentaOrigen = transferencia.getOperacionByIdoperacion().getCuentaByIdcuenta();

        balanceOriginal = balanceOriginal.subtract(valorARestar);
        cuentaOrigen.setSaldo(balanceOriginal);

        this.cuentaRepository.save(cuentaOrigen);
        this.transferenciaRepository.save(transferencia);

        return "redirect:/";
    }

    @GetMapping("/cambiodivisa")
    public String cambioDivisaEmpresa(@RequestParam("id") Integer idCuenta, Model model)
    {
        LocalDate today = LocalDate.now();

        CuentaEntity cuenta = this.cuentaRepository.findById(idCuenta).orElse(null);
        ClienteEntity cliente = cuenta.getClienteByIdcliente();

        CambiodivisaEntity cambiodivisa = new CambiodivisaEntity();

        OperacionEntity operacion = new OperacionEntity();
        operacion.setIdcliente(cliente.getIdcliente());
        operacion.setCuentaByIdcuenta(cuenta);
        operacion.setFecha(Date.valueOf(today));

        this.operacionRepository.save(operacion);

        cambiodivisa.setOperacionByIdoperacion(operacion);
        cambiodivisa.setCantidadcompra(null);
        cambiodivisa.setComision("2");
        cambiodivisa.setMonedacompra("euro");
        cambiodivisa.setMonedaventa("dolar");

        model.addAttribute("clienteTransferencia", cliente);
        model.addAttribute("cuentaTransferencia", cuenta);
        model.addAttribute("cambiodivisa", cambiodivisa);

        return "empresaCambiodivisa";
    }

    @PostMapping("/cambiodivisa/enviar")
    public String tramitarCambiodivisaEmpresa(@ModelAttribute("cambiodivisa") CambiodivisaEntity cambiodivisa, Model model)
    {
        BigDecimal balanceOriginal = cambiodivisa.getOperacionByIdoperacion().getCuentaByIdcuenta().getSaldo();
        BigDecimal valorASumar = new BigDecimal(cambiodivisa.getCantidadventa());

        CuentaEntity cuentaOrigen = cambiodivisa.getOperacionByIdoperacion().getCuentaByIdcuenta();

        balanceOriginal = balanceOriginal.add(valorASumar.multiply(BigDecimal.valueOf(0.85)));
        cuentaOrigen.setSaldo(balanceOriginal);
        cambiodivisa.setCantidadcompra(String.valueOf(valorASumar.multiply(BigDecimal.valueOf(0.85))));

        this.cuentaRepository.save(cuentaOrigen);
        this.cambiodivisaRepository.save(cambiodivisa);

        return "redirect:/";
    }
}
