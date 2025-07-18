package co.edu.uniquindio.alquiler.model;

import co.edu.uniquindio.alquiler.Listas.ListaSimple;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformacionPasajero {

    private TipoAsiento tipoAsiento;
    private Pasajero pasajero;
    private ListaSimple<Equipaje> piezasEquipaje;
    private ListaSimple<Mascota> mascotas;
}
