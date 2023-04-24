package com.taw.grupo5.controller;

import com.taw.grupo5.dao.EmpresaRepository;
import com.taw.grupo5.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    protected EmpresaRepository empresaRepository;

    @GetMapping("")
    public String empresaInicio(Model model)
    {
        return "empresaInicio";
    }

    @GetMapping("/registro")
    public String empresaRegistro(Model model)
    {
        EmpresaEntity empresa = new EmpresaEntity();
        model.addAttribute("empresa", empresa);
        return "empresaRegistro";
    }

    @PostMapping("/registro/guardar")
    public String empresaRegistroGuardar(@ModelAttribute("empresa") EmpresaEntity empresa, Model model)
    {
        this.empresaRepository.save(empresa);

        return "redirect:/";
    }

}
