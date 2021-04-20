package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Actividades;
import com.ipt.dashboard.entity.Proyecto;
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

import javax.management.StringValueExp;
import java.util.Optional;

@Controller
@RequestMapping("/actividades")
public class ActividadesController {

    @Autowired
    ActividadesRepository actividadesRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProyectoRepository proyectoRepository;

    @GetMapping("/nuevo")
    public String nuevoActividad(@RequestParam("idProyecto") int idProyecto,
                                 Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        model.addAttribute("idProyecto", idProyecto);
        return "/actividad/nuevaActividad";
    }

    @GetMapping("/editar")
    public String editarActividad() {

        return "";
    }

    @PostMapping("/guardar")
    public String guardarActividad(Actividades actividades,
                                   RedirectAttributes attr) {
        Proyecto proyecto = null;
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(actividades.getIdproyecto());

        if (proyectoOptional.isPresent()) {
            proyecto = proyectoOptional.get();
        }
        System.out.println(actividades.getIdactividad() + "##################");
        if (actividades.getIdactividad() == 0) {
            attr.addFlashAttribute("msgCreatAct", "Actividad Creada Exitosamente");
        } else {
            attr.addFlashAttribute("msgEditAct", "Actividad Actualizada Exitosamente");
        }

        actividadesRepository.save(actividades);

        return "redirect:/proyecto/edit?id=" + proyecto.getIdproyecto();
    }

    @GetMapping("/borrar")
    public String borrarActividad(@RequestParam("id") int id, //id actividad
                                  @RequestParam("idProyecto") int idProyecto,
                                  RedirectAttributes attr) {

        Optional<Actividades> actividadesOptional = actividadesRepository.findById(id);

        if (actividadesOptional.isPresent()) {
            actividadesRepository.deleteById(id);
            attr.addFlashAttribute("msgDeleteAct", "Actividad borrada exitosamente");
        }
        return "redirect:/proyecto/edit?id=" + idProyecto; //debe mandar id(id proyecto en el otro controller)
    }

}
