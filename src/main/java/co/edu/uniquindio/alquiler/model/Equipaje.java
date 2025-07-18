package co.edu.uniquindio.alquiler.model;

import co.edu.uniquindio.alquiler.Listas.ListaSimple;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Equipaje {
    private double pesoTotal;
    private ListaSimple<ObjetosLlevar> objetosLlevar;
    private int codigoEquipaje;
    private double alto;
    private double largo;
    private double ancho;
    private double dimensionesTotales;
    private double kilosEquipajeAdicional;
    private double costoTotalEquipaje;
    private TipoEquipaje tipoEquipaje;
}
