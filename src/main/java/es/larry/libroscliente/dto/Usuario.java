package es.larry.libroscliente.dto;

public class Usuario {

    private int id;
    private String nombre;
    private String rol;
    private int puntos;

    public Usuario(int id, String nombre, String rol, int puntos) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.puntos = puntos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public int getPuntos() {
        return puntos;
    }
}
