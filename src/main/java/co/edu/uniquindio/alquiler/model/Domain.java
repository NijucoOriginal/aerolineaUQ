package co.edu.uniquindio.alquiler.model;

import co.edu.uniquindio.alquiler.enums.EstadoRecibo;
import co.edu.uniquindio.alquiler.exceptions.PromedioBajoException;
import co.edu.uniquindio.alquiler.exceptions.ReciboExistenteException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Domain {

    private static Domain domain;

    public FactorySAC factorySAC=FactorySAC.getInstance();


    private Domain(){

    }

    public static Domain getInstance(){
        if(domain == null){
            domain = new Domain();
        }

        return domain;
    }

    //Metodos

    public Estudiante accederPlataforma(String palabraClave,String identificacion,String iconoClave) {
        ArrayList<Estudiante> listaEstudiantes=factorySAC.getSac().factoryEstudiante.getEstudiantes();
        for(int i=0;i<listaEstudiantes.size();i++)
        {
            if(listaEstudiantes.get(i).getPalabraClave().equals(palabraClave)&&listaEstudiantes.get(i).getId().equals(identificacion)&&listaEstudiantes.get(i).getIconoClave().equals(iconoClave))
            {
                return listaEstudiantes.get(i);
            }
        }
        return null;
    }

    public void agregarRecibodePago(Estudiante estudiante,ReciboPago reciboPago,Materia materia) throws PromedioBajoException, ReciboExistenteException {
        for(int i=0;i<factorySAC.getSac().factoryEstudiante.getEstudiantes().size();i++)
        {
            if(!reciboExistente(estudiante,reciboPago))
            {
                if(verificarPromedio(materia))
                {
                    factorySAC.getSac().factoryEstudiante.getEstudiantes().get(i).listaRecibosPago.add(reciboPago);
                    i=factorySAC.getSac().factoryEstudiante.getEstudiantes().size();

                }
                else
                {
                    throw new PromedioBajoException("No puede habilitar esa materia, su promedio es muy bajo o es mayor igual a 3.0");
                }
            }
            else
            {
                throw new ReciboExistenteException();
            }
        }
    }

    public boolean verificarPromedio(Materia materia) {
        if(materia.getNotaDefinitiva()>1.9&&materia.getNotaDefinitiva()<3.0)
        {
            return true;
        }
        return false;
    }

    public ReciboPago encontrarReciboPagar(Estudiante estudiante,int codigoReferencia) {
        for(int i=0;i<estudiante.listaRecibosPago.size();i++)
        {
            if(estudiante.listaRecibosPago.get(i).getNumeroReferencia()==codigoReferencia)
            {
                return estudiante.listaRecibosPago.get(i);
            }
        }
        return null;
    }

    public boolean reciboExistente(Estudiante estudiante,ReciboPago reciboNuevo) {
        for(int i=0;i<factorySAC.getSac().factoryEstudiante.getEstudiantes().size();i++)
        {
            for(int j=0;j<factorySAC.getSac().factoryEstudiante.getEstudiantes().get(i).listaRecibosPago.size();j++)
            {
                if(factorySAC.getSac().factoryEstudiante.getEstudiantes().get(i).listaRecibosPago.get(j).getNumeroReferencia()==reciboNuevo.getNumeroReferencia())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void eliminarReciboPago(Estudiante estudiante,ReciboPago reciboPagoEliminar) {
        for(int i=0;i<factorySAC.getSac().factoryEstudiante.getEstudiantes().size();i++)
        {
            if(estudiante.equals(factorySAC.getSac().factoryEstudiante.getEstudiantes().get(i)))
            {
                factorySAC.getSac().factoryEstudiante.getEstudiantes().get(i).listaRecibosPago.remove(reciboPagoEliminar);
            }
        }
    }

    public void pagarRecibo(Estudiante estudiante,int numeroReferencia) {
        for(int i=0;i<estudiante.getListaRecibosPago().size();i++)
        {
            ReciboPago reciboPagoEncontrado=buscarReciboPago(estudiante,numeroReferencia);
            if(reciboPagoEncontrado!=null)
            {
                estudiante.getListaRecibosPago().get(i).setEstadoRecibo(EstadoRecibo.PAGADO);
                estudiante.getListaRecibosPago().get(i).setFechaPago(LocalDate.now());
                estudiante.getListaRecibosPago().get(i).setFechaVencimiento(null);
                i=estudiante.getListaRecibosPago().size();
            }
        }
    }

    public ReciboPago buscarReciboPago(Estudiante estudiante,int numeroReferencia) {
        for (int i = 0; i < estudiante.getListaRecibosPago().size(); i++) {

            if (estudiante.getListaRecibosPago().get(i).getNumeroReferencia() == numeroReferencia && estudiante.getListaRecibosPago().get(i).getEstadoRecibo() == EstadoRecibo.GENERADO)
            {
                return estudiante.getListaRecibosPago().get(i);
            }
        }
        return null;
    }

    public void ejecutarArchivoHtml(String rutaArchivo)  {
        File archivoHTML = new File(rutaArchivo+".html");
        try
        {
            Desktop.getDesktop().browse(archivoHTML.toURI());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}