package es.larry.libroscliente.controller;

import es.larry.libroscliente.dto.HistorialLibrosUser;
import es.larry.libroscliente.dto.LibroFila;
import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.view.ListaLibrosView;
import java.util.ArrayList;
import java.util.List;

public class ListaLibrosController {

    private final ListaLibrosView listaLibrosView;
    private final LoginService service;
    private List<HistorialLibrosUser> listaActiva;

    public ListaLibrosController(ListaLibrosView listaLibrosView) {
        this.listaLibrosView = listaLibrosView;
        this.service = new LoginService();
        this.listaActiva = new ArrayList<>();

        initView();
        initEvents();
    }

    private void initView() {
        cargarLibros();
        cargarPrestamosActivos();
        listaLibrosView.show();
    }

    private void initEvents() {
        listaLibrosView.setOnReservar(this::reservarLibro);
        listaLibrosView.setOnListaEspera(this::apuntarListaEspera);
        listaLibrosView.setOnDevolver(this::devolverLibro);
    }

    private void cargarLibros() {
        try {
            List<LibroFila> lista = service.listarLibros();
            listaLibrosView.cargarLibros(lista);
        } catch (Exception e) {
            listaLibrosView.mostrarError("No se pudieron cargar los libros.");
        }
    }

    private void cargarPrestamosActivos() {
        try {
            listaActiva = service.obtenerMiHistorialCompleto();
            listaLibrosView.setListaActiva(listaActiva);
        } catch (Exception e) {
            listaActiva = new ArrayList<>();
            listaLibrosView.setListaActiva(listaActiva);
        }
    }

    private void refrescarLibros() {
        cargarLibros();
        cargarPrestamosActivos();
    }

    private void reservarLibro(LibroFila libro) {
        try {
            service.reservarLibro(libro.getId());
            refrescarLibros();
            listaLibrosView.mostrarMensajeReserva(libro);
        } catch (Exception e) {
            listaLibrosView.mostrarError("No se pudo reservar el libro.");
        }
    }

    private void apuntarListaEspera(LibroFila libro) {
        listaLibrosView.mostrarMensajeListaEspera(libro);
    }

    private void devolverLibro(LibroFila libro) {
        try {
            int status = service.devolverLibro(libro.getId());

            if (status == 200) {
                refrescarLibros();
                listaLibrosView.mostrarMensajeDevolucion(libro);
            } else {
                listaLibrosView.mostrarError("No se pudo devolver el libro.");
            }
        } catch (Exception e) {
            listaLibrosView.mostrarError("Error al devolver el libro.");
        }
    }
}