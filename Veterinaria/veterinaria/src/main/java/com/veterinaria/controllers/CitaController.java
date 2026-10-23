package com.veterinaria.controllers;

import com.veterinaria.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;

    @GetMapping
    public String mostrarCitas(Model modelo) {
        modelo.addAttribute("citas", citaService.obtenerProgramadas());
        return "cita/listado";
    }
}
