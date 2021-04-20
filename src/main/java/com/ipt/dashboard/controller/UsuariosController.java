package com.ipt.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuariosController {



    @GetMapping("/editar")
    public String editarUsuarios(@RequestParam("id") String id,
                                 Model model){
        return "/";
    }

    @GetMapping("/borrar")
    public String borrarUsuarios(@RequestParam("id") String id,
                                 RedirectAttributes attr){

        return "/";
    }

}
