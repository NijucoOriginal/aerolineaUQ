package co.edu.uniquindio.alquiler.Listas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Nodo<T> {

    private T objeto;
    private Nodo<T> siguiente;

    public Nodo(T objeto) {
        this.objeto = objeto;
    }
}
