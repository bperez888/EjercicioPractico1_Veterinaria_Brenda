package com.veterinaria.controllers;

import com.veterinaria.domain.MensajeContacto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class InicioController {

    private final MessageSource fuenteMensajes;

    @GetMapping("/")
    public String mostrarInicio(Model modelo) {
        modelo.addAttribute("mensajeContacto", new MensajeContacto());
        return "inicio";
    }

    @PostMapping("/consultas/enviar")
    public String recibirConsulta(@Valid @ModelAttribute("mensajeContacto") MensajeContacto mensajeContacto,
            BindingResult resultado, RedirectAttributes atributos) {
        if (resultado.hasErrors()) {
            return "inicio";
        }
        String gracias = fuenteMensajes.getMessage("consulta.gracias",
                new Object[]{mensajeContacto.getNombre()}, LocaleContextHolder.getLocale());
        atributos.addFlashAttribute("avisoExito", gracias);
        return "redirect:/#consultas";
    }
}
