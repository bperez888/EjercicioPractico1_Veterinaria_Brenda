package com.veterinaria.service;

import com.veterinaria.domain.TipoServicio;
import java.util.List;
import java.util.Optional;

public interface TipoServicioService {

    /** Tipos ordenados por nombre con su lista de servicios ya cargada (para contarlos). */
    List<TipoServicio> obtenerTodosConServicios();

    /** Tipos ordenados por nombre, pensado para llenar listas desplegables. */
    List<TipoServicio> obtenerTodos();

    Optional<TipoServicio> buscarPorId(Integer id);

    TipoServicio registrar(TipoServicio tipoServicio);

    /**
     * @throws IllegalArgumentException si el tipo no existe
     * @throws IllegalStateException si el tipo todavía tiene servicios asociados
     */
    void eliminarPorId(Integer id);
}
