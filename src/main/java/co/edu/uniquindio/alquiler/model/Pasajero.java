package co.edu.uniquindio.alquiler.model;


import co.edu.uniquindio.alquiler.Listas.ListaSimple;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pasajero extends Persona {

    private Pasaporte pasaporte;
    private double carteraVirtual;
    private double pesoPasajero;
    private boolean registrado;
    private ListaSimple<Tiquete> tiquetes;
    private int numeroTarjetaCredito;
    private String conrasenia;
}
