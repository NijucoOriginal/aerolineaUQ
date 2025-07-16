package co.edu.uniquindio.alquiler.Listas;

import lombok.*;

@Getter
@Setter
public class ListaSimple<T> {

    private int cantidad;
    private Nodo<T> primero;

    public ListaSimple() {
        cantidad=0;
        primero=null;
    }

    public void agregarInicio(Nodo<T> primero) {
        if(estaVacia())
        {
            this.primero=primero;
        }
        else
        {
            primero.setSiguiente(this.primero);
            this.primero=primero;
        }
        cantidad++;
    }

    public void agregarFinal(Nodo<T> nodoFinal) {
        if(estaVacia())
        {
            this.primero=nodoFinal;
        }
        else
        {
            
        }
    }

    public boolean estaVacia() {
        return primero==null;
    }
}
