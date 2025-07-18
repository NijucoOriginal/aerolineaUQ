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

    public void agregarFinal(Nodo<T> nodoFinal,int longitud,Nodo<T> nodoMovimiento) {
        if(estaVacia())
        {
            this.primero=nodoFinal;
        }
        else
        {
            if(longitud==0)
            {
                nodoMovimiento.setSiguiente(nodoFinal);
            }
            else
            {
                longitud--;
                agregarFinal(nodoFinal,longitud,nodoMovimiento.getSiguiente());
            }
        }
    }

    public void eliminarAlInicio(int longitud,Nodo<T> siguiente) {
        Nodo<T> enviar;
        if(longitud>0)
        {
            if(longitud==cantidad)
            {
                primero=primero.getSiguiente();
                enviar=primero;
            }
            else
            {
                siguiente=siguiente.getSiguiente();
                enviar=siguiente;
            }
            eliminarAlInicio(longitud-1,enviar);
        }
        else
        {
            cantidad--;
        }
    }

    public boolean estaVacia() {
        return primero==null;
    }
}
