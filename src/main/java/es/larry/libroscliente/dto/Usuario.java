package es.larry.libroscliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario {

    private String usuario;
    private String nombreCompleto;
    private String email;
    private int puntos;
    private int preguntesEncertades;

    public Usuario() {
    }

    public Usuario(String usuario, String nombreCompleto, String email, int puntos,int preguntesEncertades) {
        this.usuario = usuario;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.puntos = puntos;
        this.preguntesEncertades = preguntesEncertades;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPreguntesEncertades() {
        return preguntesEncertades;
    }

    public void setPreguntesEncertades(int preguntesEncertades) {
        this.preguntesEncertades = preguntesEncertades;
    }
}
