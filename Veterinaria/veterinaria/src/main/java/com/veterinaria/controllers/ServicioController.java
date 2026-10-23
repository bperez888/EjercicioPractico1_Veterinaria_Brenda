package com.veterinaria.controllers;

import com.veterinaria.domain.Servicio;
import com.veterinaria.domain.TipoServicio;
import com.veterinaria.service.ServicioService;
import com.veterinaria.service.TipoServicioService;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/servicios")
public class ServicioController {

    private static final String VISTA_LISTADO = "servicio/listado";
    private static final String VISTA_FORMULARIO = "servicio/formulario";
    private static final String REDIRIGIR_LISTADO = "redirect:/servicios";

    private final ServicioService servicioService;
    private final TipoServicioService tipoServicioService;
    private final MessageSource fuenteMensajes;

    @GetMapping
    public String mostrarServicios(Model modelo) {
        modelo.addAttribute("servicios", servicioService.obtenerTodos());
        return VISTA_LISTADO;
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model modelo) {
        Servicio nuevo = new Servicio();
        nuevo.setTipoServicio(new TipoServicio());
        modelo.addAttribute("servicio", nuevo);
        cargarTipos(modelo);
        return VISTA_FORMULARIO;
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model modelo,
            RedirectAttributes atributos) {
        Optional<Servicio> encontrado = servicioService.buscarPorId(id);
        if (encontrado.isEmpty()) {
            atributos.addFlashAttribute("avisoError", texto("servicio.error.noExiste"));
            return REDIRIGIR_LISTADO;
        }
        modelo.addAttribute("servicio", encontrado.get());
        cargarTipos(modelo);
        return VISTA_FORMULARIO;
    }

    @PostMapping("/guardar")
    public String guardarServicio(@Valid @ModelAttribute("servicio") Servicio servicio,
            BindingResult resultado, Model modelo, RedirectAttributes atributos) {
        boolean sinTipo = servicio.getTipoServicio() == null || servicio.getTipoServicio().getId() == null;
        if (sinTipo) {
            if (servicio.getTipoServicio() == null) {
                servicio.setTipoServicio(new TipoServicio());
            }
            resultado.addError(new FieldError("servicio", "tipoServicio.id", null, false,
                    new String[]{"servicio.tipo.requerido"}, null, "Debe seleccionar un tipo de servicio."));
        }
        if (resultado.hasErrors()) {
            cargarTipos(modelo);
            return VISTA_FORMULARIO;
        }
        boolean esNuevo = servicio.getId() == null;
        servicioService.registrar(servicio);
        atributos.addFlashAttribute("avisoExito", texto(esNuevo ? "mensaje.agregado" : "mensaje.modificado"));
        return REDIRIGIR_LISTADO;
    }

    @PostMapping("/eliminar")
    public String borrarServicio(@RequestParam("id") Integer id, RedirectAttributes atributos) {
        try {
            servicioService.eliminarPorId(id);
            atributos.addFlashAttribute("avisoExito", texto("mensaje.eliminado"));
        } catch (IllegalArgumentException ex) {
            atributos.addFlashAttribute("avisoError", texto("servicio.error.noExiste"));
        } catch (IllegalStateException ex) {
            atributos.addFlashAttribute("avisoError", texto("servicio.error.conCitas"));
        } catch (RuntimeException ex) {
            atributos.addFlashAttribute("avisoError", texto("mensaje.error.inesperado"));
        }
        return REDIRIGIR_LISTADO;
    }

    private void cargarTipos(Model modelo) {
        modelo.addAttribute("tipos", tipoServicioService.obtenerTodos());
    }

    private String texto(String codigo) {
        return fuenteMensajes.getMessage(codigo, null, LocaleContextHolder.getLocale());
    }
}
