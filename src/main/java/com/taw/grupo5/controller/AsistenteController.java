package com.taw.grupo5.controller;

import com.taw.grupo5.dao.ClienteRepository;
import com.taw.grupo5.dao.ConversacionRepository;
import com.taw.grupo5.dao.EmpleadoRepository;
import com.taw.grupo5.dao.MensajeRepository;
import com.taw.grupo5.entity.MensajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    /*
           AMBAS PARTES


    <%if(){%>
    <%}else{%>
    <%}%>



     */



    /*
    @PostMapping("/login")
    public String dologin(Model model, HttpSession session, @RequestParam("user")String usuario){
        String vuelta = "prueba";


        EmpleadoEntity empleado = clienteRepository.autenticar(usuario);
        if(empleado!=null){
            vuelta = "redirect:/asistente";
            model.addAttribute("empleado", empleado);
        }

        return vuelta;
    }
*/
    public void doChatear(Model model, int id_conversacion){
        model.addAttribute("conversacion", conversacionRepository.findById(id_conversacion).orElse(null));
        MensajeEntity nuevoMensaje = new MensajeEntity();
        model.addAttribute("mensaje", nuevoMensaje);
    }



    @PostMapping("/enviar")
    public String doNuevoMensaje(@ModelAttribute("mensaje")MensajeEntity mensaje) {

        /*                                  IMPORTANTE - LEER
        Como se comenta en la vista del chat, aquí se añade de manualmente la fecha.

        Lo que he hecho es crear un objeto Date y formatearlo usando SimpleDateFormat para que sea legible.
        Una vez obtenido el string de la fecha y hora, hago append a mensaje.contenido INCLUYENDO entre contenido
        y fecha 4 espacios extras. Esto es porque SimpleDateFormat guarda la fecha con una extensión de 15 caracteres,
        a los que les sumo los 2 corchetes.
        Los corchetes son puramente estéticos, se pueden quitar modificando en la vista del chat las líneas que
        muestran mensaje.getContenido pasando de -17 a -15.

        Finalmente guardo este cambio con setContenido y guardo en la base de datos.

        Si hubiera un atributo no habría que hacer este proceso ya que ya estaría correcto desde el formulario.
        Como ya se ha dicho en el formulario, habrían dos opciones:
        a) Guardar en base de datos como Date y formatear en la tabla de la conversación
        b) Guardar en base de datos como String ya formateada en el formulario

        He mostrado la opción b por simpleza y comodidad, así a ciegas creo que funcionaría el código en el caso
        real.
         */

        int id_conver = mensaje.getConversacionByIdconversacion().getIdconversacion();
        String vuelta;

        String mensaje_bruto = mensaje.getContenido();

        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy, HH:mm");
        String str_fecha = formato.format(fecha);

        mensaje.setContenido(mensaje_bruto + " [" + str_fecha + "]");

        mensajeRepository.save(mensaje);

        if(mensaje.getEnviadoporasistente()>0){             //si es asistente
            vuelta = "redirect:/asistente/conversacion?id=";
        }else{                                              //si es cliente
            vuelta = "redirect:/asistente/conversar?id=";
        }
        return vuelta + id_conver;
    }

    /*
           LADO DEL CLIENTE
     */
    @GetMapping("/misconversaciones")
    public String doListarCliente(Model model, @RequestParam("id") int id_cliente) {
    //public String doListarCliente(Model model, HttpSession session) {
        //session.getAttributeNames(); PENDIENTE
        model.addAttribute("lista", conversacionRepository.findAll());
        model.addAttribute("cliente", clienteRepository.findById(id_cliente).orElse(null));
        return "conversacionesCliente";
    }

    @GetMapping("/conversar")
    public String doChatearComoCliente(Model model, @RequestParam("id") int id_conversacion){
        doChatear(model, id_conversacion);
        model.addAttribute("esAsistente", 0);
        return "chat";
    }

    /*
            LADO DEL ASISTENTE
     */
    @GetMapping("") //CAMBIAR TRAS HACER HTTPSESSION
    public String doListarAsistente(Model model, @RequestParam("id") int id_asistente){
        model.addAttribute("lista", conversacionRepository.findAll());
        model.addAttribute("empleado", empleadoRepository.findById(id_asistente).orElse(null));
        return "conversacionesAsistente";
    }

    @GetMapping("/volver")
    public String doVolverAsistente(@RequestParam("id") int id_asistente){
        return "redirect:/asistente?id="+id_asistente;
    }

    @GetMapping("/conversacion")
    public String doChatearComoAsistente(Model model, @RequestParam("id") int id_conversacion){
        doChatear(model, id_conversacion);
        model.addAttribute("esAsistente", 1);
        return "chat";
    }
}
