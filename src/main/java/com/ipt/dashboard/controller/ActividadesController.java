package com.ipt.dashboard.controller;

import com.ipt.dashboard.repository.ActividadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/actividades")
public class ActividadesController {

    @Autowired
    ActividadesRepository actividadesRepository;

    @GetMapping("/nuevo")
    public String crearActividad(){
        return "//";
    }
    @GetMapping("/editar")
    public String editarActividad(){
        return "";
    }
    @PostMapping("/guardar")
    public String crearActividad(){
        return "";
    }
    @GetMapping("/borrar")
    public String crearActividad(@RequestParam("id") int id,
                                 RedirectAttributes attr){
        return "";
    }

}
