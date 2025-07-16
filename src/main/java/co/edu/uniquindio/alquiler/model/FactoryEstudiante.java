package co.edu.uniquindio.alquiler.model;

import java.util.ArrayList;

public class FactoryEstudiante {

    private String Id;
    private ArrayList<Estudiante> Estudiantes;

    private static FactoryEstudiante factory;

    private FactoryEstudiante(){
        this.Estudiantes=new ArrayList();
        this.Id="123";
    }

    public static FactoryEstudiante getInstance(){
        if(factory == null){
            factory = new FactoryEstudiante();
        }

        return factory;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return Estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        Estudiantes = estudiantes;
    }


}
