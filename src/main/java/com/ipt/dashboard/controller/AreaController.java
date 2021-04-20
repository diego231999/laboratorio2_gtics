package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Area;
import com.ipt.dashboard.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    AreaRepository areaRepository;

    @GetMapping("/listar")
    public String listarArea(Model model){

        //List<Area> lisOfAreas = areaRepository.findAll();

        return "area/lista";
    }
}
