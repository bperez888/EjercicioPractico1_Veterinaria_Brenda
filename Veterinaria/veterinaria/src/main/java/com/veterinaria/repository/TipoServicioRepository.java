package com.veterinaria.repository;

import com.veterinaria.domain.TipoServicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicio, Integer> {

    List<TipoServicio> findAllByOrderByNombreAsc();

    @Query("SELECT t FROM TipoServicio t LEFT JOIN FETCH t.servicios ORDER BY t.nombre")
    List<TipoServicio> buscarTodosConServicios();
}
