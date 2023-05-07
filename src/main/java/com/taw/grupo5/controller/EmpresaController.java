package com.taw.grupo5.controller;

/**
 * @author Jesús Ariza
 */

import com.taw.grupo5.dao.*;
import com.taw.grupo5.entity.*;
import com.taw.grupo5.ui.FiltroClientesEmpresa;
import com.taw.grupo5.ui.FiltroOperacionesEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
        cuenta.setNumerocuenta(null);

        estadoCuentaNueva.setIdtipoestado(1);

        cuenta.setTipoestadoByIdestado(estadoCuentaNueva);
        cuenta.setSaldo(null);
        cuenta.setFechaapertura(Date.valueOf(today));
        this.cuentaRepository.save(cuenta);

        return "redirect:/";
    }

    @GetMapping("/accederdaralta")
    public String accederDarAlta(Model model)
    {
        EmpresaEntity empresa = new EmpresaEntity();

        model.addAttribute("empresaDaAlta", empresa);

        return "empresaAccederDarAlta";
    }

    @PostMapping("/accederdaralta/ir")
    public String irAccederDarAlta(@ModelAttribute("empresaDaAlta") EmpresaEntity empresaDaAlta, Model model)
    {
        return "redirect:/empresa/dardealta?id=" + empresaDaAlta.getIdempresa();
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
        cuenta.setNumerocuenta(null);

        estadoCuentaNueva.setIdtipoestado(1);

        cuenta.setTipoestadoByIdestado(estadoCuentaNueva);
        cuenta.setSaldo(null);
        cuenta.setFechaapertura(Date.valueOf(today));
        this.cuentaRepository.save(cuenta);

        return "redirect:/";
    }

    protected String procesadorFiltrado(Integer idCliente, FiltroClientesEmpresa filtroClientes, FiltroOperacionesEmpresa filtroOperaciones, Model model)
    {
        ClienteEntity cliente = this.clienteRepository.findById(idCliente).orElse(null);
        List<ClienteEntity> listaClientes = this.clienteRepository.buscarPorEmpresa(cliente.getEmpresaByIdempresa().getIdempresa());
        List<ClienteEntity> listaClientesFiltrada = new ArrayList<>();
        List<Integer> lista = new ArrayList<>();

        if(filtroClientes == null)
            filtroClientes = new FiltroClientesEmpresa();

        if(filtroOperaciones == null)
            filtroOperaciones = new FiltroOperacionesEmpresa();

        for(ClienteEntity c : listaClientes)
        {

            if(filtroClientes.getFechaMinima().before(c.getFechainicio()) && filtroClientes.getFechaMaxima().after(c.getFechainicio()) && filtroClientes.getTipoCliente().equals(""))
                listaClientesFiltrada.add(c);
            else if(filtroClientes.getFechaMinima().before(c.getFechainicio()) && filtroClientes.getFechaMaxima().after(c.getFechainicio()) && filtroClientes.getTipoCliente().equals("socio") && c.getTipoclienteByIdtipocliente().getIdtipocliente() == 2)
                listaClientesFiltrada.add(c);
            else if (filtroClientes.getFechaMinima().before(c.getFechainicio()) && filtroClientes.getFechaMaxima().after(c.getFechainicio()) && filtroClientes.getTipoCliente().equals("autorizado") && c.getTipoclienteByIdtipocliente().getIdtipocliente() == 3)
                listaClientesFiltrada.add(c);
        }

        for(ClienteEntity c : listaClientes)
        {
                lista.add(c.getIdcliente());
        }

        List<OperacionEntity> listaOperaciones = this.operacionRepository.buscarPorEmpresa(lista);
        List<OperacionEntity> listaOperacionesFiltrada = new ArrayList<>();
        ClienteEntity clienteOperacionFiltrada;

        Integer idTipoCliente;

        for(OperacionEntity op : listaOperaciones)
        {
            clienteOperacionFiltrada = this.clienteRepository.findById(op.getIdcliente()).orElse(null);
            idTipoCliente = clienteOperacionFiltrada.getTipoclienteByIdtipocliente().getIdtipocliente();

            if(filtroOperaciones.getFechaMinima().before(op.getFecha()) && filtroOperaciones.getFechaMaxima().after(op.getFecha()) && filtroOperaciones.getTipoCliente().equals(""))
                listaOperacionesFiltrada.add(op);
            else if(filtroOperaciones.getFechaMinima().before(op.getFecha()) && filtroOperaciones.getFechaMaxima().after(op.getFecha()) && filtroOperaciones.getTipoCliente().equals("socio") && idTipoCliente == 2)
                listaOperacionesFiltrada.add(op);
            else if (filtroOperaciones.getFechaMinima().before(op.getFecha()) && filtroOperaciones.getFechaMaxima().after(op.getFecha()) && filtroOperaciones.getTipoCliente().equals("autorizado") && idTipoCliente == 3)
                listaOperacionesFiltrada.add(op);
        }

        filtroClientes.setIdClienteDelPortal(idCliente);
        filtroOperaciones.setIdClienteDelPortal(idCliente);

        model.addAttribute("clientePortal", cliente);
        model.addAttribute("listaClientes", listaClientesFiltrada);
        model.addAttribute("listaOperaciones", listaOperacionesFiltrada);
        model.addAttribute("filtroClientes", filtroClientes);
        model.addAttribute("filtroOperaciones", filtroOperaciones);

        return "empresaPortalEmpleado";
    }

    @GetMapping("/portal")
    public String portalEmpleado(Model model, HttpSession session)
    {
        ClienteEntity clienteEmpresa = (ClienteEntity) session.getAttribute("clienteEmpresa");

        return procesadorFiltrado(clienteEmpresa.getIdcliente(), null, null, model);
    }

    @PostMapping("/portal/filtrarClientes")
    public String portalEmpleadoFiltrado(@ModelAttribute("filtroClientes") FiltroClientesEmpresa filtroClientes, @ModelAttribute("filtroOperaciones") FiltroOperacionesEmpresa filtroOperaciones, Model model)
    {
        return procesadorFiltrado(filtroClientes.getIdClienteDelPortal(), filtroClientes, null, model);
    }

    @PostMapping("/portal/filtrarOperaciones")
    public String portalOperacionesFiltrado(@ModelAttribute("filtroOperaciones") FiltroOperacionesEmpresa filtroOperaciones, @ModelAttribute("filtroClientes") FiltroClientesEmpresa filtroClientes, Model model)
    {
        return procesadorFiltrado(filtroOperaciones.getIdClienteDelPortal(), null, filtroOperaciones, model);
    }

    @GetMapping("/editarcliente")
    public String editarCliente(Model model, HttpSession session)
    {
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("clienteEmpresa");

        Integer idCliente = cliente.getIdcliente();

        ClienteEntity clienteEmpresa = this.clienteRepository.findById(idCliente).orElse(null);

        session.setAttribute("clienteEmpresa", clienteEmpresa);

        model.addAttribute("clienteAEditar", clienteEmpresa);

        return "empresaEditarCliente";
    }

    @PostMapping("/editarcliente/guardar")
    public String guardarEditarCliente(@ModelAttribute("clienteAEditar") ClienteEntity cliente, Model model)
    {
        this.clienteRepository.save(cliente);

        return "redirect:/empresa/portal";
    }

    @GetMapping("/editarempresa")
    public String editarEmpresa(Model model, HttpSession session)
    {
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("clienteEmpresa");

        Integer idCliente = cliente.getIdcliente();

        ClienteEntity clienteEmpresa = this.clienteRepository.findById(idCliente).orElse(null);

        session.setAttribute("clienteEmpresa", clienteEmpresa);

        EmpresaEntity empresa = clienteEmpresa.getEmpresaByIdempresa();

        model.addAttribute("empresaAEditar", empresa);

        return "empresaEditarEmpresa";
    }

    @PostMapping("/editarempresa/guardar")
    public String guardarEditarEmpresa(@ModelAttribute("empresaAEditar") EmpresaEntity empresa, Model model)
    {
        this.empresaRepository.save(empresa);

        return "redirect:/empresa/portal";
    }

    @GetMapping("/bloquear")
    public String bloquearCliente(@RequestParam("id") Integer idCuenta, Model model)
    {
        CuentaEntity cuenta = this.cuentaRepository.findById(idCuenta).orElse(null);
        TipoestadoEntity estadoCuenta = new TipoestadoEntity();

        estadoCuenta.setIdtipoestado(4);

        cuenta.setTipoestadoByIdestado(estadoCuenta);

        this.cuentaRepository.save(cuenta);

        return "redirect:/empresa/portal";
    }

    @GetMapping("/solicitarActivacion")
    public String solicitarActivacion(@RequestParam("id") Integer idCuenta, Model model)
    {
        CuentaEntity cuenta = this.cuentaRepository.findById(idCuenta).orElse(null);
        TipoestadoEntity estadoCuenta = new TipoestadoEntity();

        estadoCuenta.setIdtipoestado(3);

        cuenta.setTipoestadoByIdestado(estadoCuenta);

        this.cuentaRepository.save(cuenta);

        return "redirect:/empresa/portal";
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
        CuentaEntity cuentaDestino = this.cuentaRepository.findById(transferencia.getIdcuentadestino()).orElse(null);
        LocalDate today = LocalDate.now();

        BigDecimal balanceOrigen = transferencia.getOperacionByIdoperacion().getCuentaByIdcuenta().getSaldo();
        BigDecimal balanceDestino = cuentaDestino.getSaldo();

        if(balanceOrigen == null)
            balanceOrigen = BigDecimal.valueOf(0);

        if(balanceDestino == null)
            balanceDestino = BigDecimal.valueOf(0);

        BigDecimal cantidadTransferencia = transferencia.getCantidad();

        transferencia.setFechainstruccion(Date.valueOf(today));

        CuentaEntity cuentaOrigen = transferencia.getOperacionByIdoperacion().getCuentaByIdcuenta();

        balanceOrigen = balanceOrigen.subtract(cantidadTransferencia);
        cuentaOrigen.setSaldo(balanceOrigen);

        balanceDestino = balanceDestino.add(cantidadTransferencia);
        cuentaDestino.setSaldo(balanceDestino);

        this.cuentaRepository.save(cuentaOrigen);
        this.cuentaRepository.save(cuentaDestino);
        this.transferenciaRepository.save(transferencia);

        return "redirect:/empresa/portal";
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

        return "redirect:/empresa/portal";
    }

    @GetMapping("/cerrarsesion")
    public String cerrarSesionClienteEmpresa(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
