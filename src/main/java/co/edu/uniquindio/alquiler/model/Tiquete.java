package co.edu.uniquindio.alquiler.model;

import co.edu.uniquindio.alquiler.Listas.ListaSimple;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tiquete {
    private String codigoTiquete;
    private LocalDateTime fechaIda;
    private LocalDateTime fechaVuelta;
    private Ruta ruta;
    private TipoVuelo tipoVuelo;
    private ListaSimple<InformacionPasajero> pasajeros;
    private double valorTotal;
    private double valorTiquete;
    private double valorEquipamiento;
    private double valorMascota;
    private String nombrePasajeroAdjunto;
    private String codigoPasajeroAdjunto;

}
