package com.example.cookievery.models;

public class Cliente {

    private String identificacion;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private String password;

    public Cliente() {
    }

    public Cliente(String identificacion, String nombre, String correo, String telefono, String direccion, String password) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.password = password;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return !(getIdentificacion().isEmpty() ||
                getNombre().isEmpty() ||
                getTelefono().isEmpty() ||
                getCorreo().isEmpty() ||
                getDireccion().isEmpty() ||
                getPassword().isEmpty());
    }
}
