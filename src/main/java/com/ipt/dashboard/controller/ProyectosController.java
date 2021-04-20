package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Actividades;
import com.ipt.dashboard.entity.Proyecto;
import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.ActividadesRepository;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/proyecto")
public class ProyectosController {
    @Autowired
    ProyectoRepository proyectoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ActividadesRepository actividadesRepository;

    @GetMapping("/listar")
    public String listProyecto(Model model) {
        model.addAttribute("listaProyectos", proyectoRepository.findAll());
        return "/proyecto/listaProyectos";
    }

    @GetMapping("/new")
    public String createProyecto(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "/proyecto/nuevoProyecto";
    }

    @PostMapping("/save")
    public String saveProyecto(Proyecto proyecto,
                               RedirectAttributes attr) {
        if (proyecto.getIdproyecto() == 0) {
            attr.addFlashAttribute("msgSave", "Proyecto Creado Exitosamente");
        } else {
            attr.addFlashAttribute("msgEdit", "Proyecto Actualizado Exitosamente");
        }
        proyectoRepository.save(proyecto);
        return "redirect:/proyecto/listar";
    }

    @GetMapping("/edit")
    public String editProyecto(@RequestParam("id") int id,
                               Model model) {
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(id);
        List<Usuario> usuarioList = usuarioRepository.findAll();
        List<Actividades> actividadesList = actividadesRepository.findByIdproyecto(id);
        double sumaPesos=actividadesRepository.sumaPesos(id);
        double sumaPesosTotal=actividadesRepository.sumaPesosTotal(id);


        if (proyectoOptional.isPresent()) {
            Proyecto proyecto = proyectoOptional.get();
            model.addAttribute("proyecto", proyecto);
            model.addAttribute("listaUsuarios", usuarioList);
            model.addAttribute("listaActividades", actividadesList);
            model.addAttribute("pesoActividades", sumaPesosTotal);
            model.addAttribute("pesoActividadesFinalizadas", sumaPesos);
            return "/proyecto/editarProyecto";
        } else {
            return "redirect:/proyecto/listar";
        }
    }

    @GetMapping("/delete")
    public String deleteProyecto(@RequestParam("id") int id,
                                 RedirectAttributes attr) {
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(id);
        if (proyectoOptional.isPresent()) {
            proyectoRepository.deleteById(id);
            attr.addFlashAttribute("msgDelete", "Proyecto borrado exitosamente");
        }
        return "redirect:/proyecto/listar";
    }

}
