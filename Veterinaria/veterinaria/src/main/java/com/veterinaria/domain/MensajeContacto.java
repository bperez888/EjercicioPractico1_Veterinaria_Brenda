package com.veterinaria.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MensajeContacto {

    @NotBlank(message = "escriba su nombre.")
    @Size(max = 100, message = "El nombre es muy largo.")
    private String nombre;

    @NotBlank(message = "escriba su correo electrónico.")
    @Email(message = "El correo electrónico no es valido.")
    private String correo;

    @NotBlank(message = "escriba el nombre de su mascota.")
    @Size(max = 100, message = "El nombre de la mascota es demasiado largo.")
    private String mascota;

    @NotBlank(message = "Escriba su mensaje.")
    @Size(max = 1000, message = "El mensaje no puede superar los 1000 caracteres.")
    private String mensaje;
}
