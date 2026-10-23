package com.veterinaria.service;

import com.veterinaria.domain.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {

    List<Servicio> obtenerTodos();

    Optional<Servicio> buscarPorId(Integer id);

    Servicio registrar(Servicio servicio);


    void eliminarPorId(Integer id);
}
