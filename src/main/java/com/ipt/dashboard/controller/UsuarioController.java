package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public String listarUsuarios(Model model){
        List<Usuario> listaDeUsuarios = usuarioRepository.findAll();
        model.addAttribute("listaDeUsuarios", listaDeUsuarios);
        return "/usuario/listar";
    }
}
