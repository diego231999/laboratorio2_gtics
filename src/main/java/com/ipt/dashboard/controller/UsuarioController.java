package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Area;
import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.AreaRepository;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.dom4j.rule.Mode;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AreaRepository areaRepository;

    @GetMapping("/listar")
    public String listarUsuarios(Model model){
        List<Usuario> listaDeUsuarios = usuarioRepository.findAll();
        model.addAttribute("listaDeUsuarios", listaDeUsuarios);
        return "/usuario/listar";
    }

    @GetMapping("/form")
    public String crearUsuario(Model model){
        List<Area> areaList = areaRepository.findAll();

        model.addAttribute("listaArea",areaList);
        return "/usuario/nuevoUsuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(Usuario usuario,
                                 RedirectAttributes attr){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getCorreo());
        if(usuarioOptional.isPresent()){
            attr.addFlashAttribute("msgEdit","Usuario Creado Exitosamente");
        }else{
            attr.addFlashAttribute("msgSave","Usuario Actualizado Exitosamente");
        }
        usuarioRepository.save(usuario);
        return "redirect:/usuario/crear";
    }

    @GetMapping("/editar")
    public String editarUsuarios(@RequestParam("correo") String correo,
                                 Model model){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(correo);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            model.addAttribute("usuario",usuario);
            return "/usuario/editarUsuario";
        }else {
            return "redirect:/usuario/listar";
        }
    }

    @GetMapping("/borrar")
    public String borrarUsuarios(@RequestParam("correo") String correo,
                                 RedirectAttributes attr){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(correo);
        if(usuarioOptional.isPresent()){
            usuarioRepository.deleteById(correo);
            attr.addFlashAttribute("msgDelete","Usuario borrado exitosamente");
        }
        return "redirect:/usuario/lista";
    }
}
