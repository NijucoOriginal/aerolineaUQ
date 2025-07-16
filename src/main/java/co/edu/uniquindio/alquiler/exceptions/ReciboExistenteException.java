package co.edu.uniquindio.alquiler.exceptions;

public class ReciboExistenteException extends RuntimeException {

    public ReciboExistenteException() {
        super("El numero de referencia del recibo de ya existe");
    }
}
