package co.edu.uniquindio.alquiler.model;

import co.edu.uniquindio.alquiler.Listas.ListaSimple;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aeronave {
    private String nombre;
    private int asientosTotales;
    private double carga;
    private int asientosEjecutivos;
    private int asientosEconomicos;
    private ListaSimple<Tripulante> tripulantes;
    private ListaSimple<Pasajero> pasajeros;
    private BicolaSimple<CarroEmbarque> carrosEmbarque;
    private Ruta rutaAdjunta;
}
