package com.grupo5.grupo5.controller;

import com.grupo5.grupo5.dao.ClienteRepository;
import com.grupo5.grupo5.entity.ClienteEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class ClienteController {

    @Autowired
    protected ClienteRepository clienteRepository;


    @GetMapping("/")
    public String doListar(Model model, HttpSession session){
        String urlto = "clientes";

        List<ClienteEntity> lista = this.clienteRepository.findAll();
        model.addAttribute("clientes", lista);

        return urlto;
    }

    @PostMapping("/filtrar")
    public String doFiltrar (@RequestParam("filtro") String filtro, Model model){
        List<ClienteEntity> lista = this.clienteRepository.buscarPorNombre(filtro);
        model.addAttribute("clientes", lista);
        return "customers";
    }

    @GetMapping("/editar")
    public String doEditar (@RequestParam("id") Integer idcliente, Model model) {
        ClienteEntity cliente = this.clienteRepository.findById(idcliente).orElse(null);
        model.addAttribute("cliente", cliente);

        return "customer";
    }
    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("cliente") ClienteEntity cliente){
        this.clienteRepository.save(cliente);
        return "redirect:/";
    }

    @GetMapping("/borrar")
    public String doBorrar(@RequestParam("id") Integer idcliente){
        this.clienteRepository.deleteById(idcliente);
        return "redirect:/";
    }
}
