package es.larry.libroscliente.controller;

import es.larry.libroscliente.dto.LibroFila;
import es.larry.libroscliente.view.ListaLibrosView;

import java.util.List;

public class ListaLibrosController {

    private ListaLibrosView listaLibrosView;

    public ListaLibrosController(ListaLibrosView listaLibrosView) {
        this.listaLibrosView = listaLibrosView;
        initEvent();
    }

    private void initEvent(){
        List<LibroFila> lista = List.of(
                new LibroFila(1, "El Quijote", "Cervantes", "Novela",true),
                new LibroFila(2, "La sombra del viento", "Carlos Ruiz Zafón", "Misterio",false),
                new LibroFila(3, "1984", "George Orwell", "Distopía",true),
                new LibroFila(4, "La sombra del viento", "Carlos Ruiz Zafón", "Misterio",false));
        listaLibrosView.cargarLibros(lista);
        listaLibrosView.show();
    }
}
