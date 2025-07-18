package co.edu.uniquindio.alquiler.model;


import co.edu.uniquindio.alquiler.Listas.ListaSimple;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tripulante extends Persona {

    private String ocupacion;
    private ListaSimple<EstudioRealizado> estudiosRealizados;
}
