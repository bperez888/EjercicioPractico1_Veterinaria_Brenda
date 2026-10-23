package com.veterinaria.service;

import com.veterinaria.domain.TipoServicio;
import java.util.List;
import java.util.Optional;

public interface TipoServicioService {

    List<TipoServicio> obtenerTodosConServicios();

    List<TipoServicio> obtenerTodos();

    Optional<TipoServicio> buscarPorId(Integer id);

    TipoServicio registrar(TipoServicio tipoServicio);

    void eliminarPorId(Integer id);
}
