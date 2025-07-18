package co.edu.uniquindio.alquiler.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pasaporte {
    private String nombre;
    private int numeroPasaporte;
    private LocalDateTime fechaNacimiento;
    private String nacionalidad;
}
