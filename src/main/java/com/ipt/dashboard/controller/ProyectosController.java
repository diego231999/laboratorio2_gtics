package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Proyecto;
import com.ipt.dashboard.repository.ProyectoRepository;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/proyecto")
public class ProyectosController {
    @Autowired
    ProyectoRepository proyectoRepository;
    UsuarioRepository usuarioRepository;
    @GetMapping("/listar")
    public String listProyecto(Model model){
        model.addAttribute("listaProyectos", proyectoRepository.findAll());
        return "/proyecto/listaProyectos";
    }
    @GetMapping("/new")
    public String createProyecto(Model model){
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "/proyecto/nuevoProyecto";
    }

    @PostMapping("/save")
    public String saveProyecto(Proyecto proyecto,
                               RedirectAttributes attr){
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(proyecto.getIdproyecto());
        if(proyectoOptional.isPresent()){
            attr.addFlashAttribute("msgEdit","Usuario Creado Exitosamente");
        }else{
            attr.addFlashAttribute("msgSave","Usuario Actualizado Exitosamente");
        }
        proyectoRepository.save(proyecto);
        return "redirect:/proyecto/list";
    }

    @GetMapping("/edit")
    public String editProyecto(@RequestParam("id") int id,
                               Model model){

        return "";
    }

    @GetMapping("/delete")
    public String deleteProyecto(){
        return "";
    }

}
