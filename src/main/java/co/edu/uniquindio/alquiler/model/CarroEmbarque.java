package co.edu.uniquindio.alquiler.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarroEmbarque {

    private int codigo;
    private double pesoMaximo;
    private double pesoActual;
    private Conductor conductor;

}
