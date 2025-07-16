package co.edu.uniquindio.alquiler.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FactoryReciboPago {

    private String Id;
    private ArrayList<ReciboPago> listaRecibosPago;

    private static FactoryReciboPago factory;

    private FactoryReciboPago(){
        this.listaRecibosPago=new ArrayList<>();
        this.Id="1234";
    }

    public static FactoryReciboPago getInstance(){
        if(factory == null){
            factory = new FactoryReciboPago();
        }

        return factory;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public ArrayList<ReciboPago> getListaRecibosPago() {
        return listaRecibosPago;
    }

    public void setListaRecibosPago(ArrayList<ReciboPago> listaRecibosPago) {
        this.listaRecibosPago = listaRecibosPago;
    }

}
