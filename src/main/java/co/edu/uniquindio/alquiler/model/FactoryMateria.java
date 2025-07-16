package co.edu.uniquindio.alquiler.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FactoryMateria {

    private String Id;
    private ArrayList<Materia> listaMaterias;

    private static FactoryMateria factory;

    private FactoryMateria(){
        this.listaMaterias=new ArrayList<>();
        this.Id="123";
    }

    public static FactoryMateria getInstance(){
        if(factory == null){
            factory = new FactoryMateria();
        }

        return factory;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public ArrayList<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(ArrayList<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }
}
