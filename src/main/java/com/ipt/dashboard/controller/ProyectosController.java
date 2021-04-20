package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Proyecto;
import com.ipt.dashboard.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proyecto")
public class ProyectosController {
    @Autowired
    ProyectoRepository proyectoRepository;


    @GetMapping("/list")
    public String listProyecto(Model model){
        model.addAttribute("lista", proyectoRepository.findAll());
        return "/proyecto/listaProyectos";
    }
    @GetMapping("/new")
    public String createProyecto(){
        return "proyecto/nuevoProyecto";
    }

    @PostMapping("/save")
    public String saveProyecto(Proyecto proyecto){
        proyectoRepository.save(proyecto);
        return "redirect:/proyecto/list";
    }

}
