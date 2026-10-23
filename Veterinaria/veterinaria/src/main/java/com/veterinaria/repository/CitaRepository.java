package com.veterinaria.repository;

import com.veterinaria.domain.Cita;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    @EntityGraph(attributePaths = {"servicio", "servicio.tipoServicio"})
    List<Cita> findAllByOrderByFechaAsc();

    boolean existsByServicioId(Integer idServicio);
}
