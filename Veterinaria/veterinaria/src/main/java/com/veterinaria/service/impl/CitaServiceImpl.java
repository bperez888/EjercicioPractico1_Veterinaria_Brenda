package com.veterinaria.service.impl;

import com.veterinaria.domain.Cita;
import com.veterinaria.repository.CitaRepository;
import com.veterinaria.service.CitaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> obtenerProgramadas() {
        return citaRepository.findAllByOrderByFechaAsc();
    }
}
