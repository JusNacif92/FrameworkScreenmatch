package com.aluracursos.screenmatch;

public class ErrorEnConversionDeDuracionException extends Throwable {
    private String mensaje;

    public ErrorEnConversionDeDuracionException(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
