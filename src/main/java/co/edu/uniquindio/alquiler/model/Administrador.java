package co.edu.uniquindio.alquiler.model;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Administrador extends Persona{
    private String contrasenia;

}
