package com.veterinaria.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Objeto de formulario (no es entidad): el formulario de consultas no usa base de datos. */
@Getter
@Setter
@NoArgsConstructor
public class MensajeContacto {

    @NotBlank(message = "Indique su nombre.")
    @Size(max = 100, message = "El nombre es demasiado largo.")
    private String nombre;

    @NotBlank(message = "Indique su correo electrónico.")
    @Email(message = "El correo electrónico no es válido.")
    private String correo;

    @NotBlank(message = "Indique el nombre de su mascota.")
    @Size(max = 100, message = "El nombre de la mascota es demasiado largo.")
    private String mascota;

    @NotBlank(message = "Escriba su mensaje.")
    @Size(max = 1000, message = "El mensaje no puede superar los 1000 caracteres.")
    private String mensaje;
}
