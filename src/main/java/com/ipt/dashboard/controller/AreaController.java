package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Area;
import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.AreaRepository;
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
@RequestMapping("/area")
public class AreaController {

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public String listarArea(Model model){
        List<Area> lisOfAreas = areaRepository.findAll();
        model.addAttribute("lisOfAreas", lisOfAreas);
        return "area/lista";
    }

    @GetMapping("/crear")
    public String nuevaArea(){
        return "area/nuevaArea";
    }

    @GetMapping("/editar")
    public String editarArea(@RequestParam("id") int id,
                             Model model){

        Optional<Area> areaOptional = areaRepository.findById(id);
        if(areaOptional.isPresent()){
            Area area = areaOptional.get();
            model.addAttribute("area", area);

            // se buscan los usuarios
            List<Usuario> usuarioList = usuarioRepository.findAllByIdarea(id);
            model.addAttribute("usuarioList", usuarioList);

            return "area/editarArea";
        }else {
            return "redirect:/area/listar";
        }
    }

    @PostMapping("/guardar")
    public String guardarArea(Area area, RedirectAttributes attributes){
        areaRepository.save(area);

        attributes.addFlashAttribute("msg", "se modifico el area"+area.getNombrearea());

        return "redirect:/area/listar";
    }


    @GetMapping("/borrar")
    public String borrarArea(@RequestParam("id") int id, RedirectAttributes attributes){
        Optional<Area> areaOptional = areaRepository.findById(id);
        if(areaOptional.isPresent()){

            Area area = areaOptional.get();
            attributes.addFlashAttribute("msg", "se elimino el area"+area.getNombrearea());

            areaRepository.deleteById(id);
        }
        return "redirect:/area/listar";
    }


}
