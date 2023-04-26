package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ConversacionRepository;
import com.taw.grupo5.dao.EmpleadoRepository;
import com.taw.grupo5.dao.MensajeRepository;
import com.taw.grupo5.entity.ConversacionEntity;
import com.taw.grupo5.entity.MensajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asistente")
public class AsistenteController {

    @Autowired
    ConversacionRepository conversacionRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    MensajeRepository mensajeRepository;

    //@GetMapping("/conversaciones")  //si gestor = asistente
    @GetMapping("")                 // si no lo son
    public String doListar(Model model, @RequestParam("id") int id_asistente){
        model.addAttribute("lista", conversacionRepository.findAll());
        model.addAttribute("empleado", empleadoRepository.findById(id_asistente).orElse(null));
        return "conversaciones";              //si gestor = asistente
        //return "asistente";                 // si no lo son + REFACTOR CAMBIAR NOMBRE
    }

    @GetMapping("/conversacion")
    public String doChatear(Model model, @RequestParam("id") int id_conversacion){
        model.addAttribute("conversacion", conversacionRepository.findById(id_conversacion).orElse(null));
        MensajeEntity nuevoMensaje = new MensajeEntity();
        model.addAttribute("mensaje", nuevoMensaje);
        return "chat";
    }

    @GetMapping("/volver")
    public String doVolver(@RequestParam("id") int id_asistente){
        //return "redirect:/asistente/conversaciones?id="+id_asistente;  //si gestor = asistente
        return "redirect:/asistente?id="+id_asistente; //si no
    }

    @PostMapping("/enviar")
    public String doNuevoMensaje(@ModelAttribute("mensaje")MensajeEntity mensaje) {
        int id_conver = mensaje.getConversacionByIdconversacion().getIdconversacion();

        mensajeRepository.save(mensaje);
        int id = mensaje.getConversacionByIdconversacion().getIdconversacion();
        return "redirect:/asistente/conversacion?id="+id;
    }
}
