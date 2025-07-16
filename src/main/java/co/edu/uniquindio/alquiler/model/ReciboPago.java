package co.edu.uniquindio.alquiler.model;

import co.edu.uniquindio.alquiler.enums.EstadoRecibo;

import java.time.LocalDate;

public class ReciboPago {

    private String IDestudiante;
    private EstadoRecibo estadoRecibo;
    private LocalDate fechaExpedicion;
    private LocalDate fechaPago;
    private final double valorPagar=300;
    private LocalDate fechaVencimiento;
    private String nombreMateria;
    private  int numeroReferencia;
    private String programaPerteneciente;
    private String codigoMateria;

    public ReciboPago(String IDestudiante, EstadoRecibo estadoRecibo, LocalDate fechaExpedicion, LocalDate fechaPago, LocalDate fechaVencimiento,String nombreMateria,int numeroReferencia,String programaPerteneciente,String codigoMateria) {
        this.IDestudiante = IDestudiante;
        this.estadoRecibo = estadoRecibo;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaPago = fechaPago;
        this.fechaVencimiento = fechaVencimiento;
        this.nombreMateria = nombreMateria;
        this.numeroReferencia = numeroReferencia;
        this.programaPerteneciente = programaPerteneciente;
        this.codigoMateria = codigoMateria;
    }

    public String getIDestudiante() {
        return IDestudiante;
    }

    public void setIDestudiante(String IDestudiante) {
        this.IDestudiante = IDestudiante;
    }

    public EstadoRecibo getEstadoRecibo() {
        return estadoRecibo;
    }

    public void setEstadoRecibo(EstadoRecibo estadoRecibo) {
        this.estadoRecibo = estadoRecibo;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(int numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public String getProgramaPerteneciente() {
        return programaPerteneciente;
    }

    public void setProgramaPerteneciente(String programaPerteneciente) {
        this.programaPerteneciente = programaPerteneciente;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }
}
