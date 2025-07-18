package co.edu.uniquindio.alquiler.model;


import co.edu.uniquindio.alquiler.Listas.ListaSimple;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public abstract class Persona {
    private String nombre;
    private String documentoIdentidad;
    private String correoElectronico;
    private String telefono;
    private String telefonoEmergencia;
    private int edad;
    private String tipoSangre;
    private String nacionalidad;
}
