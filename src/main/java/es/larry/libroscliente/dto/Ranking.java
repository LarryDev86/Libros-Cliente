package es.larry.libroscliente.dto;

public class Ranking {

    private String nombreUser;
    private int puntos;

    public Ranking(String nombreUser, int puntos) {
        this.nombreUser = nombreUser;
        this.puntos = puntos;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public int getPuntos() {
        return puntos;
    }
}
