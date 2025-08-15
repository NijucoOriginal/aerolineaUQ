package co.edu.uniquindio.alquiler.model;

import lombok.Getter;
import lombok.Setter;

public class DatosSesionPasajero {

    @Getter
    @Setter
    Pasajero pasajeroActivo;

    private static DatosSesionPasajero datos;

    private DatosSesionPasajero() {

    }

    public static DatosSesionPasajero getInstance(){
        if(datos == null){
            datos = new DatosSesionPasajero();
        }

        return datos;
    }

}
