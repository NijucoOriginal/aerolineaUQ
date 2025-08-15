package co.edu.uniquindio.alquiler.model;

import lombok.Getter;
import lombok.Setter;

public class DatosSesionAdministrador {
    @Getter
    @Setter
    Administrador administradorActivo;

    private static DatosSesionAdministrador datos;

    private DatosSesionAdministrador() {

    }

    public static DatosSesionAdministrador getInstance(){
        if(datos == null){
            datos = new DatosSesionAdministrador();
        }

        return datos;
    }
}
