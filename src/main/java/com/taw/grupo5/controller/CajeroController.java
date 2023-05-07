package com.taw.grupo5.controller;

import com.taw.grupo5.dao.*;
import com.taw.grupo5.dto.*;
import com.taw.grupo5.entity.*;
import com.taw.grupo5.service.*;
import com.taw.grupo5.ui.FiltroOperacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CajeroController {

    @Autowired
    protected ClienteService clienteService;
    @Autowired
    protected CuentaService cuentaService;
    @Autowired
    protected OperacionService operacionService;
    @Autowired
    protected SacarDineroService sacarDineroService;
    @Autowired
    protected TransferenciaService transferenciaService;
    @Autowired
    protected CambioDivisaService cambioDivisaService;
    @Autowired
    protected TipoEstadoService tioTipoEstadoService;

    @GetMapping("/cajero/datos")
    public String doMostrar(@RequestParam("idCliente") Integer idCliente, Model model){

        ClienteDTO cliente = clienteService.buscarPorID(idCliente);

        model.addAttribute("cliente", cliente);

        return "cajeroCuentas";
    }

    @GetMapping("/cajero/login")
    public String doLogin() {
        return "cajeroLogin";
    }

    @PostMapping("/cajero/login")
    public String doLoggear(@RequestParam("idCliente") Integer idCliente,
                     Model model, HttpSession session){

        String urlTo = "redirect:/cajero/datos?idCliente=" + idCliente;
        ClienteDTO usuario = clienteService.buscarPorID(idCliente);
        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "/cajero/login";
        } else {
            session.setAttribute("user", usuario);
        }
        return urlTo;

    }

    @GetMapping("/cajero/datos/editar")
    public String doEditar(@RequestParam("idCliente") Integer idCliente, Model model){
        ClienteDTO cliente = clienteService.buscarPorID(idCliente);
        model.addAttribute("cliente", cliente);
        return "cajeroDatos";
    }

    @PostMapping("/cajero/datos/guardar")
    public String goGuardar(@ModelAttribute("cliente") ClienteDTO cliente){
        clienteService.guardar(cliente);
        return "redirect:/cajero/datos?idCliente=" + cliente.getIdCliente();
    }

    @GetMapping("/cajero/datos/operaciones")
    public String doListarOperaciones(@RequestParam("idCuenta") Integer idCuenta, Model model){

        CuentaDTO cuenta = cuentaService.buscarPorId(idCuenta);
        model.addAttribute("cuenta", cuenta);
        return filtro(null, cuenta, model);
    }

    @PostMapping("/cajero/datos/operaciones")
    public String doFiltrarOperaciones(Model model, FiltroOperacion filtro){

        CuentaDTO cuenta = cuentaService.buscarPorId(filtro.getIdCuenta());

        return filtro(filtro, cuenta, model);
    }

    private String filtro(FiltroOperacion filtro, CuentaDTO cuenta, Model model){
        List<OperacionDTO> operaciones;
        List<OperacionDTO> operacionesPorFecha = new ArrayList<>();

        if(filtro == null) {
            filtro = new FiltroOperacion();
        }

        if (filtro.getTipoOperacion().equals("sacarDinero")) operaciones = operacionService.buscarSacarDineroPorCuenta(cuenta.getIdcuenta());
        else if (filtro.getTipoOperacion().equals("transferencia")) operaciones = operacionService.buscarTransferenciaPorCuenta(cuenta.getIdcuenta());
        else if (filtro.getTipoOperacion().equals("cambioDivisa")) operaciones = operacionService.buscarCambioDivisaPorCuenta(cuenta.getIdcuenta());
        else operaciones = cuenta.getOperacionesByIdOperacion();

        for(OperacionDTO o : operaciones){
            if(o.getFecha().before(filtro.getFechaMaxima()) && o.getFecha().after(filtro.getFechaMinima())) operacionesPorFecha.add(o);
        }

        filtro.setIdCuenta(cuenta.getIdcuenta());

        model.addAttribute("filtro", filtro);
        model.addAttribute("listaOperaciones", operacionesPorFecha);
        model.addAttribute("cuenta", cuenta);

        return "cajeroOperaciones";
    }

    @GetMapping("/cajero/sacarDinero")
    public String doSacarDinero(@RequestParam("idCuenta") Integer idCuenta, Model model){
        CuentaDTO cuenta = cuentaService.buscarPorId(idCuenta);
        model.addAttribute("cuenta", cuenta);
        return "cajeroSacarDinero";
    }

    @PostMapping("/cajero/sacarDinero")
    public String doSacarCantidad(@RequestParam("cantidad") BigDecimal cantidad, @RequestParam("idCuenta") Integer idCuenta, Model model){

        CuentaDTO cuenta = cuentaService.buscarPorId(idCuenta);


        OperacionDTO newOp = new OperacionDTO();
        SacardineroDTO newSD = new SacardineroDTO();

        newOp.setCliente(cuenta.getCliente().getIdCliente());
        newOp.setCuenta(cuenta.getIdcuenta());
        newOp.setFecha(Date.valueOf(LocalDate.now()));

        operacionService.guardar(newOp);

        newOp = operacionService.ultima();

        newSD.setCantidad(cantidad);
        newSD.setOperacion(newOp.getIdOperacion());

        cuenta.setSaldo(cuenta.getSaldo().subtract(cantidad));

        sacarDineroService.guardar(newSD);

        List<SacardineroDTO> sd = new ArrayList<>();

        sd.add(newSD);

        newOp.setSacardineros(sd);
        newOp.setTransferencias(new ArrayList<>());
        newOp.setCambiodivisas(new ArrayList<>());

        operacionService.guardar(newOp);

        cuentaService.guardar(cuenta);

        return expulsarDinero(idCuenta, cantidad.doubleValue(), "euro(s)", model);
    }

    @GetMapping("/cajero/transferencia")
    public String doTransferencia(@RequestParam("idCuenta") Integer idCuenta, Model model){
        CuentaDTO cuenta = cuentaService.buscarPorId(idCuenta);
        model.addAttribute("cuenta", cuenta);
        model.addAttribute("error", false);
        return "cajeroTransferencia";
    }

    @PostMapping("/cajero/transferencia")
    public String doEnviarDinero(@RequestParam("cantidad") BigDecimal cantidad, @RequestParam("idCuenta") Integer idCuenta, @RequestParam("idDestino") Integer idDestino, Model model){

        CuentaDTO cuenta = cuentaService.buscarPorId(idCuenta);
        CuentaDTO destino = cuentaService.buscarPorId(idDestino);

        if(destino == null){
            model.addAttribute("cuenta", cuenta);
            model.addAttribute("error", true);
            return "cajeroTransferencia";
        }

        cuenta.setSaldo(cuenta.getSaldo().subtract(cantidad));
        destino.setSaldo(destino.getSaldo().add(cantidad));

        OperacionDTO newOp = new OperacionDTO();
        TransferenciaDTO newT = new TransferenciaDTO();

        newOp.setCliente(cuenta.getCliente().getIdCliente());
        newOp.setCuenta(cuenta.getIdcuenta());
        newOp.setFecha(Date.valueOf(LocalDate.now()));

        operacionService.guardar(newOp);

        newOp = operacionService.ultima();

        newT.setCantidad(cantidad);
        newT.setFechaEjecucion(newOp.getFecha());
        newT.setFechaInstruccion(newOp.getFecha());
        newT.setOperacion(newOp.getIdOperacion());
        newT.setIdCuentaDestino(idDestino);

        transferenciaService.guardar(newT);

        List<TransferenciaDTO> newTs = new ArrayList<>();
        newOp.setTransferencias(newTs);
        newOp.setCambiodivisas(new ArrayList<>());
        newOp.setSacardineros(new ArrayList<>());

        operacionService.guardar(newOp);

        cuentaService.guardar(cuenta);

        return "redirect:/cajero/datos?idCliente=" + cuenta.getCliente().getIdCliente();
    }

    @GetMapping("/cajero/cambioDivisa")
    public String doCambioDivisa(@RequestParam("idCuenta") Integer idCuenta, Model model){
        CuentaDTO cuenta = cuentaService.buscarPorId(idCuenta);
        model.addAttribute("cuenta", cuenta);
        return "cajeroCambioDivisa";
    }

    @PostMapping("/cajero/cambioDivisa")
    public String doCambiarYSacar(@RequestParam("cantidad") BigDecimal cantidad, @RequestParam("idCuenta") Integer idCuenta, Model model){

        BigDecimal dolarValue = new BigDecimal(1.1053);
        BigDecimal comision = new BigDecimal(0.0053);

        CuentaDTO cuenta = cuentaService.buscarPorId(idCuenta);

        cuenta.setSaldo(cuenta.getSaldo().subtract(cantidad));

        OperacionDTO newOp = new OperacionDTO();
        CambioDivisaDTO newCD = new CambioDivisaDTO();

        newOp.setCliente(cuenta.getCliente().getIdCliente());
        newOp.setCuenta(cuenta.getIdcuenta());
        newOp.setFecha(Date.valueOf(LocalDate.now()));

        operacionService.guardar(newOp);

        newOp = operacionService.ultima();

        newCD.setMonedacompra("Dólar");
        newCD.setMonedaventa("Euro");
        newCD.setCantidadcompra(Double.toString(cantidad.multiply(dolarValue).doubleValue()));
        newCD.setCantidadventa(Double.toString(cantidad.doubleValue()));
        newCD.setComision(Double.toString(comision.doubleValue()));
        newCD.setOperacion(newOp.getIdOperacion());

        cambioDivisaService.guardar(newCD);

        List<CambioDivisaDTO> newCDs = new ArrayList<>();
        newCDs.add(newCD);

        newOp.setCambiodivisas(newCDs);
        newOp.setTransferencias(new ArrayList<>());
        newOp.setSacardineros(new ArrayList<>());

        operacionService.guardar(newOp);

        cuentaService.guardar(cuenta);

        BigDecimal dolares = cantidad.multiply(dolarValue);

        return expulsarDinero(idCuenta, dolares.subtract(dolares.multiply(comision)).doubleValue(), "dólar(es)", model);
    }

    public String expulsarDinero(Integer idCuenta, double cantidad, String moneda, Model model){

        CuentaDTO cuenta = cuentaService.buscarPorId(idCuenta);

        model.addAttribute("cuenta", cuenta);
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("moneda", moneda);

        return "cajeroDineroSacado";
    }

    @GetMapping("/cajero/solicitudDesbloqueo")
    public String solicitudDesbloqueo(@RequestParam("idCuenta") Integer idCuenta){

        CuentaDTO cuenta = cuentaService.buscarPorId(idCuenta);

        cuenta.setTipoEstado(tioTipoEstadoService.buscarPorId(3));

        cuentaService.guardar(cuenta);

        return "redirect:/cajero/datos?idCliente=" + cuenta.getCliente().getIdCliente();

    }
}
