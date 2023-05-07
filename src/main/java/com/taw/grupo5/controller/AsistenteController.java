package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.ConversacionRepository;
import com.taw.grupo5.dao.EmpleadoRepository;
import com.taw.grupo5.dao.MensajeRepository;
import com.taw.grupo5.entity.ClienteEntity;
import com.taw.grupo5.entity.ConversacionEntity;
import com.taw.grupo5.entity.EmpleadoEntity;
import com.taw.grupo5.entity.MensajeEntity;
import com.taw.grupo5.ui.FiltroAsistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/asistente")
public class AsistenteController {

    @Autowired
    ConversacionRepository conversacionRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    MensajeRepository mensajeRepository;
    @Autowired
    ClienteRepository clienteRepository;


    /*--------------------------------------------------------
                   LOGIN ASISTENTE
    ----------------------------------------------------------
     */

    @GetMapping("/login")
    public String doIniciar(){
        return "asistenteLogin";
    }

    @PostMapping("/login")
    public String doLoginASISTENTE(Model model, HttpSession session, @RequestParam("nombre") String nombre){
        String vuelta = "asistenteLogin";
        EmpleadoEntity empleado = empleadoRepository.logear(nombre);
        if(empleado!=null){
            vuelta = "redirect:/asistente/conversaciones";
            session.setAttribute("usuario", empleado);
        }
        return vuelta;
    }

    @GetMapping("/cerrarSesion")
    public String doCerrarSesion(HttpSession session){
        session.invalidate();
        return "asistenteLogin";
    }


    /*-----------------------------------------------------------
           AMBAS PARTES
    -------------------------------------------------------------
     */


    public void doChatear(Model model, int id_conversacion){
        model.addAttribute("conversacion", conversacionRepository.findById(id_conversacion).orElse(null));
        MensajeEntity nuevoMensaje = new MensajeEntity();
        model.addAttribute("mensaje", nuevoMensaje);
    }
    public void doConversacion(Model model){
        MensajeEntity nuevoMensaje = new MensajeEntity();

        ConversacionEntity nuevaConversacion = new ConversacionEntity();
        //model.addAttribute("conversacion", nuevaConversacion);
        nuevoMensaje.setConversacionByIdconversacion(nuevaConversacion);

        model.addAttribute("mensaje", nuevoMensaje);
    }
    @PostMapping("/crear")
    public String doCrearNuevaConversacion(@ModelAttribute("mensaje")MensajeEntity mensaje) {
        String vuelta;

        conversacionRepository.save(mensaje.getConversacionByIdconversacion());
        mensajeRepository.save(mensaje);
        if(mensaje.getEnviadoporasistente()>0){             //si es asistente
            vuelta = "redirect:/asistente/conversacion?id=";
        }else{                                              //si es cliente
            vuelta = "redirect:/asistente/conversar?id=";
        }

        int idNuevaConversacion = mensaje.getConversacionByIdconversacion().getIdconversacion();
        return vuelta + idNuevaConversacion;
    }

    @PostMapping("/enviar")
    public String doNuevoMensaje(@ModelAttribute("mensaje")MensajeEntity mensaje) {

        int id_conver = mensaje.getConversacionByIdconversacion().getIdconversacion();
        String vuelta;

        mensajeRepository.save(mensaje);
        if(mensaje.getEnviadoporasistente()>0){             //si es asistente
            vuelta = "redirect:/asistente/conversacion?id=";
        }else{                                              //si es cliente
            vuelta = "redirect:/asistente/conversar?id=";
        }
        return vuelta + id_conver;
    }


    /*------------------------------------------------------------
           LADO DEL CLIENTE
     -------------------------------------------------------------
     */

    /*
    ClienteEntity usuario = (ClienteEntity) session.getAttribute("user");
    model.addAttribute("cliente", usuario);
    */
    @GetMapping("/misconversaciones")
    public String doListarCliente(Model model, HttpSession session) {


        ClienteEntity usuario = (ClienteEntity) session.getAttribute("user");
        model.addAttribute("cliente", usuario);
        model.addAttribute("lista", conversacionRepository.findAll());
        return "asistenteConversacionesCliente";
    }

    @GetMapping("/conversar")
    public String doChatearComoCliente(Model model, @RequestParam("id") int id_conversacion){
        doChatear(model, id_conversacion);
        model.addAttribute("esAsistente", 0);
        return "asistenteChat";
    }

    @GetMapping("/nuevaConversacion")
    public String doNuevaConversacionCliente(HttpSession session, Model model){
        doConversacion(model);


        model.addAttribute("esAsistente", 0);
        model.addAttribute("listaAsistentes", empleadoRepository.listarAsistentes());
        model.addAttribute("listaClientes", null);


        model.addAttribute("empleado", null);
        ClienteEntity usuario = (ClienteEntity) session.getAttribute("user");
        model.addAttribute("cliente", usuario);
        return "asistenteNuevoChatCliente";
    }

    @GetMapping("/cerrarConversacion")
    public String doCerrarConversacion(@RequestParam("id") int idconversacion){
        ConversacionEntity conversacion = conversacionRepository.findById(idconversacion).orElse(null);

        conversacion.setAbierto((byte) 0);
        conversacionRepository.save(conversacion);
        return "redirect:/asistente/misconversaciones";
    }


    /*----------------------------------------------------------------
            LADO DEL ASISTENTE
     -----------------------------------------------------------------
     */


    @GetMapping("/conversaciones")
    public String doListarAsistente(Model model, HttpSession session){
        EmpleadoEntity empleado = (EmpleadoEntity) session.getAttribute("usuario");
        model.addAttribute("empleado", empleado);

        model.addAttribute("lista", conversacionRepository.findAll());
        model.addAttribute("filtro", new FiltroAsistente());
        return "asistenteConversacionesAsistente";
    }

    @GetMapping("/moderar")
    public String doModerar(Model model, @RequestParam("id") int id){
        //ConversacionEntity conversacion = (ConversacionEntity) model.getAttribute("conversacion");
        ConversacionEntity conversacion = conversacionRepository.findById(id).orElse(null);
        doChatear(model, id);

        model.addAttribute("conversacionFiltrada", mensajeRepository.moderarMensajesCliente(conversacion));
        return "asistenteChatModeracion";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro")FiltroAsistente filtro,
                            Model model, HttpSession session){
        List<ConversacionEntity> lista = conversacionRepository.findAll();
        String opcion="";

        String nombreOCorreo = filtro.getNombreOCorreo();
        String asunto = filtro.getAsunto();
        Byte abierto = filtro.getAbierta();

        //NINGUNO
        if(filtro==null ||
                (nombreOCorreo.isEmpty() && abierto==null && asunto.isEmpty() )){
            opcion = "000-ninguno";
            filtro = new FiltroAsistente();
        }
        //"001-solo asunto"
        else if(nombreOCorreo.isEmpty() && abierto==null && !asunto.isEmpty()) {
            opcion = "001-solo asunto";
            lista = conversacionRepository.filtrarAsunto(asunto);
        }
        //"010-solo abierto"
        else if(nombreOCorreo.isEmpty() && abierto!=null && asunto.isEmpty()) {
            opcion = "010-solo abierto";
            lista = conversacionRepository.filtrarAbierto(abierto);
        }
        //"011- abierto y asunto"
        else if(nombreOCorreo.isEmpty() && abierto!=null && !asunto.isEmpty()) {
            opcion = "011-abierto y asunto";
            lista = conversacionRepository.filtrarAbiertoAsunto(abierto, asunto);
        }
        //"100- solo nombre"
        else if(!nombreOCorreo.isEmpty() && abierto==null && asunto.isEmpty()) {
            opcion = "100-solo nombre";
            lista = conversacionRepository.filtrarNombreOCorreo(nombreOCorreo);
        }
        //"101- nombre y asunto"
        else if(!nombreOCorreo.isEmpty() && abierto==null && !asunto.isEmpty()) {
            opcion = "101-nombre y asunto";
            lista = conversacionRepository.filtrarNombreOCorreoAsunto(nombreOCorreo, asunto);
        }
        //"110- nombre y abierto"
        else if(!nombreOCorreo.isEmpty() && abierto!=null && asunto.isEmpty()){
            opcion = "110-nombre y abierto";
            lista = conversacionRepository.filtrarNombreOCorreoAbierto(nombreOCorreo, abierto);
        }
        //"111- todos: nombre, abierto y asunto"
        else {
            opcion = "todos";
            lista = conversacionRepository.filtrarTodo(nombreOCorreo, abierto, asunto);
        }


        model.addAttribute("lista", lista);
        model.addAttribute("empleado", session.getAttribute("usuario"));
        model.addAttribute("filtro", filtro);
        return "asistenteConversacionesAsistente";
    }

    @GetMapping("/conversacion")
    public String doChatearComoAsistente(Model model, @RequestParam("id") int id_conversacion){
        doChatear(model, id_conversacion);
        model.addAttribute("esAsistente", 1);
        return "asistenteChat";
    }

    @GetMapping("/nuevoChat")
    public String doNuevaConversacionAsistente(Model model, HttpSession session){
        doConversacion(model);

        model.addAttribute("esAsistente", 1);
        model.addAttribute("listaAsistentes", null);
        model.addAttribute("listaClientes", clienteRepository.findAll());


        model.addAttribute("empleado", session.getAttribute("usuario"));
        model.addAttribute("cliente", null);
        return "asistenteNuevoChatAsistente";
    }
}
