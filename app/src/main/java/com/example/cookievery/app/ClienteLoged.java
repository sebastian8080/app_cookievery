package com.example.cookievery.app;

public class ClienteLoged {

    private String identificacion;

    private static ClienteLoged instance;

    public static ClienteLoged getInstance() {
        if (instance == null) {
            instance = new ClienteLoged();
        }
        return  instance;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public static void setInstance(ClienteLoged instance) {
        ClienteLoged.instance = instance;
    }
}
