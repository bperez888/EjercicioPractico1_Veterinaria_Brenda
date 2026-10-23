package com.veterinaria.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Entidad de solo lectura dentro de la aplicación: las citas se consultan, no se editan. */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cita")
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_mascota", nullable = false, length = 100)
    private String mascota;

    @Column(name = "nombre_propietario", nullable = false, length = 100)
    private String propietario;

    @Column(nullable = false)
    private LocalDate fecha;

    // Muchas citas se asocian a un mismo servicio.
    @ManyToOne(optional = false)
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;
}
