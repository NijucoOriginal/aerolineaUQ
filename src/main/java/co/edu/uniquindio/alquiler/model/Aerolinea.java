package co.edu.uniquindio.alquiler.model;


import co.edu.uniquindio.alquiler.Listas.ListaSimple;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aerolinea {
    private ListaSimple<Ruta> rutas;
    private ListaSimple<Aeronave> aeronaves;
    private ListaSimple<Conductor> conductores;
    private ListaSimple<Tripulante> tripulantes;
    private ListaSimple<Pasajero> pasajeros;


}
