package co.edu.uniquindio.alquiler.model;

import lombok.*;

import java.sql.Time;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ruta {
    private String origen;
    private String destino;
    private Time duracion;
    private Time horaOrigenDestino;
    private Time horaDestinoOrigen;
    private Aeronave avion;
    private double precioTiqueteEconomico;
    private double precioBoletoEjecutivo;
    private double tarifaRuta;
    private double tarifaEquipaje;
    private boolean esNacional;
}
