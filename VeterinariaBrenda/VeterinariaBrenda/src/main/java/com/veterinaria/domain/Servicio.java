package com.veterinaria.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "servicio")
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del servicio es obligatorio.")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "La tarifa es obligatoria.")
    @DecimalMin(value = "0.01", message = "La tarifa debe ser mayor que cero.")
    @Column(name = "precio", nullable = false, precision = 8, scale = 2)
    private BigDecimal tarifa;

    @NotNull(message = "La duración es obligatoria.")
    @Min(value = 1, message = "La duración debe ser de al menos 1 minuto.")
    @Column(name = "duracion_minutos", nullable = false)
    private Integer duracionMinutos;

    // Muchos servicios pertenecen a un mismo tipo de servicio.
    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_servicio_id", nullable = false)
    private TipoServicio tipoServicio;
}
