package com.veterinaria.repository;

import com.veterinaria.domain.Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    @EntityGraph(attributePaths = "tipoServicio")
    List<Servicio> findAllByOrderByNombreAsc();

    long countByTipoServicioId(Integer idTipoServicio);
}
