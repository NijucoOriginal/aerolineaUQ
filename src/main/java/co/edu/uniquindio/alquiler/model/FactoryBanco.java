package co.edu.uniquindio.alquiler.model;

import java.util.ArrayList;

public class FactoryBanco {

    private ArrayList<Banco> listaBancos;
    private String Id;

    private static FactoryBanco factory;

    private FactoryBanco(){
        this.listaBancos=new ArrayList();
        this.Id="12";
    }

    public static FactoryBanco getInstance(){
        if(factory == null){
            factory = new FactoryBanco();
        }

        return factory;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public ArrayList<Banco> getListaBancos() {
        return listaBancos;
    }

    public void setListaBancos(ArrayList<Banco> listaBancos) {
        this.listaBancos = listaBancos;
    }
}
