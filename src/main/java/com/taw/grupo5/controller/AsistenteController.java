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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;
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

    /*
           AMBAS PARTES


    <%if(){%>
    <%}else{%>
    <%}%>



     */

    @GetMapping("/")
    public String doIniciar(){
        return "prueba";
    }

    @PostMapping("/login")
    public String dologinCLIENTE(Model model, HttpSession session, @RequestParam("correo")String correo){
        String vuelta = "prueba";

        ClienteEntity cliente = clienteRepository.autenticar(correo);
        if(cliente!=null){
            vuelta = "redirect:/asistente/misconversaciones"+cliente.getIdcliente();
            //model.addAttribute("cliente", cliente);
            //session.setAttribute("usuario", cliente);
        }
        return vuelta;
    }

    @GetMapping("/login")
    public String doLoginASISTENTE(Model model, HttpSession session){
        String vuelta = "prueba";
        EmpleadoEntity empleado = empleadoRepository.getById(2);
        if(empleado!=null){
            vuelta = "redirect:/asistente/conversaciones?id=2";
            //session.setAttribute("usuario", empleado);
            model.addAttribute("empleado", empleado);
        }
        return vuelta;
    }

    @GetMapping("/cerrarSesion")
    public String doCerrarSesion(HttpSession session){
        session.invalidate();
        return "prueba";
    }




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
        // UPDATE EN MENSAJE LA CONVERSACION
        String vuelta;

        conversacionRepository.save(mensaje.getConversacionByIdconversacion());
        //mensaje.setConversacionByIdconversacion(conversacion);
        formatearFechaYEnviarMensaje(mensaje);

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

        formatearFechaYEnviarMensaje(mensaje);

        if(mensaje.getEnviadoporasistente()>0){             //si es asistente
            vuelta = "redirect:/asistente/conversacion?id=";
        }else{                                              //si es cliente
            vuelta = "redirect:/asistente/conversar?id=";
        }
        return vuelta + id_conver;
    }


    private void formatearFechaYEnviarMensaje(MensajeEntity mensaje){
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

        //PRUEBA DE QUE SIGUE FUNCIONANDO CHAT
        mensaje.setFechaenvio(null);

        Timestamp fecha = new Timestamp(System.currentTimeMillis());
        mensaje.setFechaenvio(fecha);


        /* POR SI EL FORMATO ESTÁ MAL
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str_fecha = formato.format(fecha);
            fecha = new Timestamp(System.currentTimeMillis());
        }catch(Exception e){
            fecha = null;
        }
        */
        mensajeRepository.save(mensaje);
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

    @GetMapping("/nuevaConversacion")
    public String doNuevaConversacionCliente(Model model){
        doConversacion(model);

        model.addAttribute("esAsistente", 0);
        model.addAttribute("listaAsistentes", empleadoRepository.listarAsistentes());
        model.addAttribute("listaClientes", null);
        return "nuevoChat";
    }

    @GetMapping("/cerrarConversacion")
    public String doCerrarConversacion(@RequestParam("id") int idconversacion){
        ConversacionEntity conversacion = conversacionRepository.findById(idconversacion).orElse(null);
        int idcliente = conversacion.getClienteByIdcliente().getIdcliente();

        conversacion.setAbierto((byte) 0);
        conversacionRepository.save(conversacion);
        return "redirect:/asistente/misconversaciones?id="+idcliente;
    }







    /*
            LADO DEL ASISTENTE
     */
    @GetMapping("/conversaciones") //CAMBIAR TRAS HACER HTTPSESSION
    public String doListarAsistente(Model model, @RequestParam("id") int id_asistente, HttpSession session){
        model.addAttribute("lista", conversacionRepository.findAll());
        model.addAttribute("empleado", empleadoRepository.findById(id_asistente).orElse(null));
        //model.addAttribute("opcionesAbierto", conversacionRepository.opcionesAbierto());
        model.addAttribute("filtro", new FiltroAsistente());
        session.setAttribute("usuario", empleadoRepository.findById(id_asistente).orElse(null));
        return "conversacionesAsistente";
    }
    @GetMapping("/moderar")
    public String doModerar(Model model, @RequestParam("id") int id){
        //ConversacionEntity conversacion = (ConversacionEntity) model.getAttribute("conversacion");
        ConversacionEntity conversacion = conversacionRepository.findById(id).orElse(null);
        doChatear(model, id);

        model.addAttribute("conversacionFiltrada", mensajeRepository.moderarMensajesCliente(conversacion));
        return "chatModeracion";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro")FiltroAsistente filtro,
                            @ModelAttribute("empleado")EmpleadoEntity empleado, Model model, HttpSession session){
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
        model.addAttribute("empleado", empleado);
        model.addAttribute("filtro", filtro);
        return "conversacionesAsistente"; //provisional hasta que funcione httpsession
    }

    /*
    @GetMapping("/volver")
    public String doVolverAsistente(@RequestParam("id") int id_asistente){
        return "redirect:/asistente?id="+id_asistente;
    }
*/
    @GetMapping("/conversacion")
    public String doChatearComoAsistente(Model model, @RequestParam("id") int id_conversacion){
        doChatear(model, id_conversacion);
        model.addAttribute("esAsistente", 1);
        return "chat";
    }

    @GetMapping("/nuevoChat")
    public String doNuevaConversacionAsistente(Model model){
        doConversacion(model);

        model.addAttribute("esAsistente", 1);
        model.addAttribute("listaAsistentes", null);
        model.addAttribute("listaClientes", clienteRepository.findAll());
        return "nuevoChat";
    }
}
