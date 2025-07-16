package co.edu.uniquindio.alquiler.model;

public class FactorySAC {

    private String Id;
    private SAC sac;
    private static FactorySAC factorySAC;

    private FactorySAC(){
        this.Id = "";
        sac = new SAC();
    }

    public static FactorySAC getInstance(){
        if(factorySAC == null){
            factorySAC = new FactorySAC();
        }

        return factorySAC;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public SAC getSac() {
        return sac;
    }

    public void setSac(SAC sac) {
        this.sac = sac;
    }
}
