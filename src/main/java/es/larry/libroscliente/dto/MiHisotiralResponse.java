package es.larry.libroscliente.dto;

import java.util.List;

public class MiHisotiralResponse {
    private List<HistorialLibrosUser> librosLeidos;
    private List<HistorialLibrosUser> prestamosActivos;
    private List<HistorialLibrosUser> reservasPendientes;

    public MiHisotiralResponse() {}

    public List<HistorialLibrosUser> getLibrosLeidos() {
        return librosLeidos;
    }

    public void setLibrosLeidos(List<HistorialLibrosUser> librosLeidos) {
        this.librosLeidos = librosLeidos;
    }

    public List<HistorialLibrosUser> getPrestamosActivos() {
        return prestamosActivos;
    }

    public void setPrestamosActivos(List<HistorialLibrosUser> prestamosActivos) {
        this.prestamosActivos = prestamosActivos;
    }

    public List<HistorialLibrosUser> getReservasPendientes() {
        return reservasPendientes;
    }

    public void setReservasPendientes(List<HistorialLibrosUser> reservasPendientes) {
        this.reservasPendientes = reservasPendientes;
    }
}
