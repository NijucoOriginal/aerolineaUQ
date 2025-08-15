package co.edu.uniquindio.alquiler.model;


import co.edu.uniquindio.alquiler.Listas.ListaSimple;
import co.edu.uniquindio.alquiler.utils.ArchivoUtils;
import javafx.scene.control.Alert;
import lombok.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Aerolinea {
    private ListaSimple<Ruta> rutas;
    private ListaSimple<Aeronave> aeronaves;
    private ListaSimple<Conductor> conductores;
    private ListaSimple<Tripulante> tripulantes;
    private ListaSimple<Pasajero> pasajeros;
    private ListaSimple<Administrador> administradores;

    private static Aerolinea aero;

    private Aerolinea(){
        this.administradores=new ListaSimple<>();
        this.aeronaves=new ListaSimple<>();
        this.conductores=new ListaSimple<>();
        this.pasajeros=new ListaSimple<>();
        this.rutas=new ListaSimple<>();
        this.tripulantes=new ListaSimple<>();
    }

    public static Aerolinea getInstance(){
        if(aero == null){
            aero = new Aerolinea();
        }

        return aero;
    }

    public Administrador verificarAdministrador(String ID,String contrasenia) {
        for(int i=0;i<administradores.getTamanio();i++)
        {
            Administrador administradorApoyo=administradores.buscarValorIndice(administradores.getPrimero(),i);
            if(administradorApoyo.getDocumentoIdentidad().equals(ID))
            {
                if(administradorApoyo.getContrasenia().equals(contrasenia))
                {
                    return administradorApoyo;
                }
            }
        }
        return null;
    }

    public Pasajero verificarPasajero(String ID,String contrasenia) {
        for(int i=0;i<pasajeros.getTamanio();i++)
        {
            Pasajero pasajerosApoyo=pasajeros.buscarValorIndice(pasajeros.getPrimero(),i);
            if(pasajerosApoyo.getDocumentoIdentidad().equals(ID))
            {
                if(pasajerosApoyo.getConrasenia().equals(contrasenia))
                {
                    return pasajerosApoyo;
                }
            }
        }
        return null;
    }

    public void mostrarAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Alerta");
        alert.setContentText(msg);
        alert.show();
    }

    public void mostrarInformacion(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Informacion");
        alert.setContentText(msg);
        alert.show();
    }

    public void mostrarConfirmacion(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmacion");
        alert.setContentText(msg);
        alert.show();
    }

    public void importarDatos() throws IOException {
        ArrayList<String> administradoresImportador=ArchivoUtils.leerArchivoScanner(
                "src/main/resources/Datos/Adminstradores");
        ArrayList<String> pasajerosImportador=ArchivoUtils.leerArchivoScanner(
                "src/main/resources/Datos/Adminstradores");

        for(String administrador: administradoresImportador)
        {
            String[] adminSpliteada=administrador.split(";");
            Administrador admin= (Administrador) Persona.builder().primerNombre(adminSpliteada[0]).
                                  segundoNombre(adminSpliteada[1]).
                                  primerApellido(adminSpliteada[2]).
                                  segundoApellido(adminSpliteada[3]).
                                  documentoIdentidad(adminSpliteada[4]).
                                  correoElectronico(adminSpliteada[5]).
                                  telefono(adminSpliteada[6]).
                                  telefonoEmergencia(adminSpliteada[7]).
                                  edad(Integer.parseInt(adminSpliteada[8])).
                                  nacionalidad(adminSpliteada[9]).build();
            administradores.agregarInicio(admin);
        }
    }

}
