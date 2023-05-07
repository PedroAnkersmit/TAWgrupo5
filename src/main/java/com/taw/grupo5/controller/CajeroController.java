package com.taw.grupo5.controller;

import com.taw.grupo5.dao.*;
import com.taw.grupo5.entity.*;
import com.taw.grupo5.uiCajero.FiltroOperacion;
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
    protected ClienteRepository clienteRepository;
    @Autowired
    protected CuentaRepository cuentaRepository;
    @Autowired
    protected OperacionRepository operacionRepository;
    @Autowired
    protected SacarDineroRepository sacarDineroRepository;
    @Autowired
    protected TransferenciaRepository transferenciaRepository;
    @Autowired
    protected CambioDivisaRepository cambioDivisaRepository;
    @Autowired
    protected TipoEstadoRepository tipoEstadoRepository;

    @GetMapping("/cajero/datos")
    public String doMostrar(@RequestParam("idCliente") Integer idCliente, Model model){

        ClienteEntity cliente = clienteRepository.findById(idCliente).orElse(null);

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
        ClienteEntity usuario = clienteRepository.findById(idCliente).orElse(null);
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
        ClienteEntity cliente = clienteRepository.findById(idCliente).orElse(null);
        model.addAttribute("cliente", cliente);
        return "cajeroDatos";
    }

    @PostMapping("/cajero/datos/guardar")
    public String goGuardar(@ModelAttribute("cliente") ClienteEntity cliente){
        clienteRepository.save(cliente);
        return "redirect:/cajero/datos?idCliente=" + cliente.getIdcliente();
    }

    @GetMapping("/cajero/datos/operaciones")
    public String doListarOperaciones(@RequestParam("idCuenta") Integer idCuenta, Model model){

        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);

        model.addAttribute("cuenta", cuenta);
        return filtro(null, cuenta, model);
    }

    @PostMapping("/cajero/datos/operaciones")
    public String doFiltrarOperaciones(Model model, FiltroOperacion filtro){

        CuentaEntity cuenta = cuentaRepository.findById(filtro.getIdCuenta()).orElse(null);

        return filtro(filtro, cuenta, model);
    }

    private String filtro(FiltroOperacion filtro, CuentaEntity cuenta, Model model){
        List<OperacionEntity> operaciones;
        List<OperacionEntity> operacionesPorFecha = new ArrayList<>();

        if(filtro == null) {
            filtro = new FiltroOperacion();
        }

        if (filtro.getTipoOperacion().equals("sacarDinero")) operaciones = operacionRepository.buscarSacarDineroPorCuenta(cuenta.getIdcuenta());
        else if (filtro.getTipoOperacion().equals("transferencia")) operaciones = operacionRepository.buscarTransferenciaPorCuenta(cuenta.getIdcuenta());
        else if (filtro.getTipoOperacion().equals("cambioDivisa")) operaciones = operacionRepository.buscarCambioDivisaPorCuenta(cuenta.getIdcuenta());
        else operaciones = cuenta.getOperacionsByIdcuenta();

        for(OperacionEntity o : operaciones){
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
        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);
        model.addAttribute("cuenta", cuenta);
        return "cajeroSacarDinero";
    }

    @PostMapping("/cajero/sacarDinero")
    public String doSacarCantidad(@RequestParam("cantidad") BigDecimal cantidad, @RequestParam("idCuenta") Integer idCuenta, Model model){

        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);


        OperacionEntity newOp = new OperacionEntity();
        SacardineroEntity newSD = new SacardineroEntity();

        newOp.setIdcliente(cuenta.getClienteByIdcliente().getIdcliente());
        newOp.setCuentaByIdcuenta(cuenta);
        newOp.setFecha(Date.valueOf(LocalDate.now()));

        operacionRepository.save(newOp);

        newSD.setCantidad(cantidad);
        newSD.setOperacionByIdoperacion(newOp);

        cuenta.setSaldo(cuenta.getSaldo().subtract(cantidad));

        List<SacardineroEntity> sacardineroEntityList = new ArrayList<>();
        sacardineroEntityList.add(newSD);

        sacarDineroRepository.save(newSD);

        List<SacardineroEntity> sd = new ArrayList<>();

        sd.add(newSD);

        newOp.setSacardinerosByIdoperacion(sd);
        newOp.setTransferenciasByIdoperacion(new ArrayList<>());
        newOp.setCambiodivisasByIdoperacion(new ArrayList<>());

        operacionRepository.save(newOp);

        cuentaRepository.save(cuenta);

        return expulsarDinero(idCuenta, cantidad.doubleValue(), "euro(s)", model);
    }

    @GetMapping("/cajero/transferencia")
    public String doTransferencia(@RequestParam("idCuenta") Integer idCuenta, Model model){
        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);
        model.addAttribute("cuenta", cuenta);
        model.addAttribute("error", false);
        return "cajeroTransferencia";
    }

    @PostMapping("/cajero/transferencia")
    public String doEnviarDinero(@RequestParam("cantidad") BigDecimal cantidad, @RequestParam("idCuenta") Integer idCuenta, @RequestParam("idDestino") Integer idDestino, Model model){

        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);
        CuentaEntity destino = cuentaRepository.findById(idDestino).orElse(null);

        if(destino == null){
            model.addAttribute("cuenta", cuenta);
            model.addAttribute("error", true);
            return "cajeroTransferencia";
        }

        cuenta.setSaldo(cuenta.getSaldo().subtract(cantidad));
        destino.setSaldo(destino.getSaldo().add(cantidad));

        OperacionEntity newOp = new OperacionEntity();
        TransferenciaEntity newT = new TransferenciaEntity();

        newOp.setIdcliente(cuenta.getClienteByIdcliente().getIdcliente());
        newOp.setCuentaByIdcuenta(cuenta);
        newOp.setFecha(Date.valueOf(LocalDate.now()));

        operacionRepository.save(newOp);

        newT.setCantidad(cantidad);
        newT.setFechaejecucion(newOp.getFecha());
        newT.setFechainstruccion(newOp.getFecha());
        newT.setOperacionByIdoperacion(newOp);
        newT.setIdcuentadestino(idDestino);

        transferenciaRepository.save(newT);

        List<TransferenciaEntity> newTs = new ArrayList<>();
        newOp.setTransferenciasByIdoperacion(newTs);
        newOp.setCambiodivisasByIdoperacion(new ArrayList<>());
        newOp.setSacardinerosByIdoperacion(new ArrayList<>());

        operacionRepository.save(newOp);

        cuentaRepository.save(cuenta);

        return "redirect:/cajero/datos?idCliente=" + cuenta.getClienteByIdcliente().getIdcliente();
    }

    @GetMapping("/cajero/cambioDivisa")
    public String doCambioDivisa(@RequestParam("idCuenta") Integer idCuenta, Model model){
        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);
        model.addAttribute("cuenta", cuenta);
        return "cajeroCambioDivisa";
    }

    @PostMapping("/cajero/cambioDivisa")
    public String doCambiarYSacar(@RequestParam("cantidad") BigDecimal cantidad, @RequestParam("idCuenta") Integer idCuenta, Model model){

        BigDecimal dolarValue = new BigDecimal(1.1053);
        BigDecimal comision = new BigDecimal(0.0053);

        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);

        cuenta.setSaldo(cuenta.getSaldo().subtract(cantidad));

        OperacionEntity newOp = new OperacionEntity();
        CambiodivisaEntity newCD = new CambiodivisaEntity();

        newOp.setIdcliente(cuenta.getClienteByIdcliente().getIdcliente());
        newOp.setCuentaByIdcuenta(cuenta);
        newOp.setFecha(Date.valueOf(LocalDate.now()));

        operacionRepository.save(newOp);

        newCD.setMonedacompra("Dólar");
        newCD.setMonedaventa("Euro");
        newCD.setCantidadcompra(Double.toString(cantidad.multiply(dolarValue).doubleValue()));
        newCD.setCantidadventa(Double.toString(cantidad.doubleValue()));
        newCD.setComision(Double.toString(comision.doubleValue()));
        newCD.setOperacionByIdoperacion(newOp);

        cambioDivisaRepository.save(newCD);

        List<CambiodivisaEntity> newCDs = new ArrayList<>();
        newCDs.add(newCD);

        newOp.setCambiodivisasByIdoperacion(newCDs);
        newOp.setSacardinerosByIdoperacion(new ArrayList<>());
        newOp.setTransferenciasByIdoperacion(new ArrayList<>());

        operacionRepository.save(newOp);

        cuentaRepository.save(cuenta);

        BigDecimal dolares = cantidad.multiply(dolarValue);

        return expulsarDinero(idCuenta, dolares.subtract(dolares.multiply(comision)).doubleValue(), "dólar(es)", model);
    }

    public String expulsarDinero(Integer idCuenta, double cantidad, String moneda, Model model){

        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);

        model.addAttribute("cuenta", cuenta);
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("moneda", moneda);

        return "cajeroDineroSacado";
    }

    @GetMapping("/cajero/solicitudDesbloqueo")
    public String solicitudDesbloqueo(@RequestParam("idCuenta") Integer idCuenta){

        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);

        cuenta.setTipoestadoByIdestado(tipoEstadoRepository.findById(3).orElse(null));

        cuentaRepository.save(cuenta);

        return "redirect:/cajero/datos?idCliente=" + cuenta.getClienteByIdcliente().getIdcliente();

    }
}
