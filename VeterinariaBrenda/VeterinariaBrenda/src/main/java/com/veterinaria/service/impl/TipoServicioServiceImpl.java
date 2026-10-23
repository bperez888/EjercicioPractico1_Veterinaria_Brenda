package com.veterinaria.service.impl;

import com.veterinaria.domain.TipoServicio;
import com.veterinaria.repository.ServicioRepository;
import com.veterinaria.repository.TipoServicioRepository;
import com.veterinaria.service.TipoServicioService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TipoServicioServiceImpl implements TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;
    private final ServicioRepository servicioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> obtenerTodosConServicios() {
        return tipoServicioRepository.buscarTodosConServicios();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> obtenerTodos() {
        return tipoServicioRepository.findAllByOrderByNombreAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoServicio> buscarPorId(Integer id) {
        return tipoServicioRepository.findById(id);
    }

    @Override
    @Transactional
    public TipoServicio registrar(TipoServicio tipoServicio) {
        return tipoServicioRepository.save(tipoServicio);
    }

    @Override
    @Transactional
    public void eliminarPorId(Integer id) {
        if (!tipoServicioRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe el tipo de servicio con id " + id);
        }
        if (servicioRepository.countByTipoServicioId(id) > 0) {
            throw new IllegalStateException("El tipo de servicio tiene servicios asociados.");
        }
        try {
            tipoServicioRepository.deleteById(id);
            tipoServicioRepository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("El tipo de servicio tiene datos asociados.", ex);
        }
    }
}
