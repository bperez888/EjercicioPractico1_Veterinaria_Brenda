package com.veterinaria.service;

import com.veterinaria.domain.Cita;
import java.util.List;

public interface CitaService {

    /** Citas ordenadas por fecha, con el servicio asociado (consulta de solo lectura). */
    List<Cita> obtenerProgramadas();
}
