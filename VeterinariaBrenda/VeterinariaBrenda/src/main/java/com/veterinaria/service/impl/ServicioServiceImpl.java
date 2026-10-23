package com.veterinaria.service.impl;

import com.veterinaria.domain.Servicio;
import com.veterinaria.repository.CitaRepository;
import com.veterinaria.repository.ServicioRepository;
import com.veterinaria.service.ServicioService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;
    private final CitaRepository citaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> obtenerTodos() {
        return servicioRepository.findAllByOrderByNombreAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> buscarPorId(Integer id) {
        return servicioRepository.findById(id);
    }

    @Override
    @Transactional
    public Servicio registrar(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    @Transactional
    public void eliminarPorId(Integer id) {
        if (!servicioRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe el servicio con id " + id);
        }
        if (citaRepository.existsByServicioId(id)) {
            throw new IllegalStateException("El servicio tiene citas programadas.");
        }
        try {
            servicioRepository.deleteById(id);
            servicioRepository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("El servicio tiene datos asociados.", ex);
        }
    }
}
