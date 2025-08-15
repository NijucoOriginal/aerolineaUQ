package co.edu.uniquindio.alquiler.Listas;

import lombok.*;

@Getter
@Setter
public class ListaSimple<T> {

    private int tamanio;
    private Nodo<T> primero;

    public ListaSimple() {
        tamanio=0;
        primero=null;
    }

    public void agregarInicio(T primero) {
        Nodo<T> agregar=new Nodo<>(primero);
        if(estaVacia())
        {
            this.primero=agregar;
        }
        else
        {
            agregar.setSiguiente(this.primero);
            this.primero=agregar;
        }
        tamanio++;
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
            if(longitud==tamanio)
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
            tamanio--;
        }
    }

    public T buscarValorIndice(Nodo<T> buscar,int indice) {
        if(indice==0)
        {
            return buscar.getObjeto();
        }
        return buscarValorIndice(buscar.getSiguiente(),indice-1);
    }

    public boolean estaVacia() {
        return primero==null;
    }
}
