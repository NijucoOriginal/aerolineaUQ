package co.edu.uniquindio.alquiler.model;

import java.util.ArrayList;

public class Materia {

    String programaPerteneciente;
    ArrayList<Double> listaNotas;

    double notaDefinitiva;
    String nombre;
    String codigo;

    public Materia(String programaPerteneciente, String nombre, String codigo) {
        this.programaPerteneciente = programaPerteneciente;
        this.listaNotas = new ArrayList<>();
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getProgramaPerteneciente() {
        return programaPerteneciente;
    }

    public void setProgramaPerteneciente(String programaPerteneciente) {
        this.programaPerteneciente = programaPerteneciente;
    }

    public ArrayList<Double> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(ArrayList<Double> listaNotas) {
        this.listaNotas = listaNotas;
    }

    public double getNotaDefinitiva() {
        return notaDefinitiva;
    }

    public void setNotaDefinitiva(double notaDefinitiva) {
        this.notaDefinitiva = notaDefinitiva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
