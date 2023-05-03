package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.CuentaRepository;
import com.taw.grupo5.dao.OperacionRepository;
import com.taw.grupo5.dao.SacarDineroRepository;
import com.taw.grupo5.entity.*;
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
        return "redirect:/cajero/datos?idCLiente=" + cliente.getIdcliente();
    }

    @GetMapping("/cajero/datos/operaciones")
    public String doListarOperaciones(@RequestParam("idCuenta") Integer idCuenta, Model model){
        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);
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
    public String doSacarCantidad(@RequestParam("cantidad") BigDecimal cantidad, @RequestParam("idCuenta") Integer idCuenta){

        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);

        cuenta.setSaldo(cuenta.getSaldo().subtract(cantidad));

        OperacionEntity newOp = new OperacionEntity();
        SacardineroEntity newSD = new SacardineroEntity();

        newOp.setIdcliente(cuenta.getClienteByIdcliente().getIdcliente());
        newOp.setCuentaByIdcuenta(cuenta);
        newOp.setFecha(Date.valueOf(LocalDate.now()));

        operacionRepository.save(newOp);

        newSD.setCantidad(cantidad);
        newSD.setOperacionByIdoperacion(newOp);

        sacarDineroRepository.save(newSD);

        newOp.setSacardineroByIdoperacion(newSD);

        operacionRepository.save(newOp);

        cuentaRepository.save(cuenta);

        return "redirect:/cajero/datos/operaciones?idCuenta=" + idCuenta;
    }

    @GetMapping("/cajero/sacarDinero")
    public String doTransferencia(@RequestParam("idCuenta") Integer idCuenta, Model model){
        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);
        model.addAttribute("cuenta", cuenta);
        return "cajeroSacarDinero";
    }

    @PostMapping("/cajero/sacarDinero")
    public String doEnviarDinero(@RequestParam("cantidad") BigDecimal cantidad, @RequestParam("idCuenta") Integer idCuenta, @RequestParam("idDestino") Integer idDestino){

        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);
        CuentaEntity destino = cuentaRepository.findById(idDestino).orElse(null);

        cuenta.setSaldo(cuenta.getSaldo().subtract(cantidad));

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

        sacarDineroRepository.save(newSD);

        newOp.setSacardineroByIdoperacion(newSD);

        operacionRepository.save(newOp);

        cuentaRepository.save(cuenta);

        return "redirect:/cajero/datos/operaciones?idCuenta=" + idCuenta;
    }
}
