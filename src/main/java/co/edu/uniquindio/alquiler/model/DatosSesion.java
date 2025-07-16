package co.edu.uniquindio.alquiler.model;

import java.util.ArrayList;

public class DatosSesion {

    Estudiante estudianteSeleccionado;
    private static DatosSesion datos;

    private DatosSesion(){
    }

    public static DatosSesion getInstance(){
        if(datos == null){
            datos = new DatosSesion();
        }

        return datos;
    }

    public Estudiante getEstudianteSeleccionado() {
        return estudianteSeleccionado;
    }

    public void setEstudianteSeleccionado(Estudiante estudianteSeleccionado) {
        this.estudianteSeleccionado = estudianteSeleccionado;
    }
}
