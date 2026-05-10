package es.larry.libroscliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario {

    private int id;
    private String usuario;
    private String nombreCompleto;
    private String email;
    private int puntos;
    private int preguntesEncertades;
    private String estado;
    private String ultimoAcceso;

    public Usuario() {
    }

    // Constructor para listado ADMIN
    public Usuario(
            int id,
            String usuario,
            String nombreCompleto,
            String email,
            int puntos,
            int preguntesEncertades,
            String estado,
            String ultimoAcceso
    ) {
        this.id = id;
        this.usuario = usuario;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.puntos = puntos;
        this.preguntesEncertades = preguntesEncertades;
        this.estado = estado;
        this.ultimoAcceso = ultimoAcceso;
    }

    // Constructor simplificado
    public Usuario(
            String usuario,
            String nombreCompleto,
            String email,
            int puntos,
            int preguntesEncertades
    ) {
        this.usuario = usuario;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.puntos = puntos;
        this.preguntesEncertades = preguntesEncertades;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getPreguntesEncertades() {
        return preguntesEncertades;
    }

    public String getEstado() {
        return estado;
    }

    public String getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setPreguntesEncertades(int preguntesEncertades) {
        this.preguntesEncertades = preguntesEncertades;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setUltimoAcceso(String ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }
}