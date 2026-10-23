package com.veterinaria.controllers;

import com.veterinaria.domain.TipoServicio;
import com.veterinaria.service.TipoServicioService;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tipos-servicio")
public class TipoServicioController {

    private static final String VISTA_LISTADO = "tipo-servicio/listado";
    private static final String VISTA_FORMULARIO = "tipo-servicio/formulario";
    private static final String REDIRIGIR_LISTADO = "redirect:/tipos-servicio";

    private final TipoServicioService tipoServicioService;
    private final MessageSource fuenteMensajes;

    @GetMapping
    public String mostrarTipos(Model modelo) {
        modelo.addAttribute("tipos", tipoServicioService.obtenerTodosConServicios());
        return VISTA_LISTADO;
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model modelo) {
        modelo.addAttribute("tipoServicio", new TipoServicio());
        return VISTA_FORMULARIO;
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model modelo,
            RedirectAttributes atributos) {
        Optional<TipoServicio> encontrado = tipoServicioService.buscarPorId(id);
        if (encontrado.isEmpty()) {
            atributos.addFlashAttribute("avisoError", texto("tipo.error.noExiste"));
            return REDIRIGIR_LISTADO;
        }
        modelo.addAttribute("tipoServicio", encontrado.get());
        return VISTA_FORMULARIO;
    }

    @PostMapping("/guardar")
    public String guardarTipo(@Valid @ModelAttribute("tipoServicio") TipoServicio tipoServicio,
            BindingResult resultado, RedirectAttributes atributos) {
        if (resultado.hasErrors()) {
            return VISTA_FORMULARIO;
        }
        boolean esNuevo = tipoServicio.getId() == null;
        tipoServicioService.registrar(tipoServicio);
        atributos.addFlashAttribute("avisoExito", texto(esNuevo ? "mensaje.agregado" : "mensaje.modificado"));
        return REDIRIGIR_LISTADO;
    }

    @PostMapping("/eliminar")
    public String borrarTipo(@RequestParam("id") Integer id, RedirectAttributes atributos) {
        try {
            tipoServicioService.eliminarPorId(id);
            atributos.addFlashAttribute("avisoExito", texto("mensaje.eliminado"));
        } catch (IllegalArgumentException ex) {
            atributos.addFlashAttribute("avisoError", texto("tipo.error.noExiste"));
        } catch (IllegalStateException ex) {
            atributos.addFlashAttribute("avisoError", texto("tipo.error.conServicios"));
        } catch (RuntimeException ex) {
            atributos.addFlashAttribute("avisoError", texto("mensaje.error.inesperado"));
        }
        return REDIRIGIR_LISTADO;
    }

    private String texto(String codigo) {
        return fuenteMensajes.getMessage(codigo, null, LocaleContextHolder.getLocale());
    }
}
