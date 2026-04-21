package es.larry.libroscliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class HistorialLibrosUser {

    private String autor;
    private String estado;
    private Date fecha;
    @JsonProperty("libroId")
    private int idLibro;
    private String titulo;

    public HistorialLibrosUser() {
    }

    public HistorialLibrosUser(String estado, String autor, Date fecha, int idLibro, String titulo) {
        this.estado = estado;
        this.autor = autor;
        this.fecha = fecha;
        this.idLibro = idLibro;
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    @Override
    public String toString() {
        return "HistorialLibrosUser{" +
                "autor='" + autor + '\'' +
                ", estado='" + estado + '\'' +
                ", fecha=" + fecha +
                ", idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
