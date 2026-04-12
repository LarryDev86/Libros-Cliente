package es.larry.libroscliente.controller;

import es.larry.libroscliente.dto.Usuario;
import es.larry.libroscliente.view.ListarUserView;

import java.util.List;

public class ListaUsersController {

    private ListarUserView listarUserView;

    public ListaUsersController(ListarUserView listarUserView) {
        this.listarUserView = listarUserView;
        initEvent();
    }

    private void initEvent(){
        List<Usuario> listaUsuarios = List.of(
                new Usuario(1,"Sandra","USER",5),
                new Usuario(2, "Marcos", "USER", 10),
                new Usuario(3, "Elena", "USER", 1),
                new Usuario(4, "Roberto", "USER", 8),
                new Usuario(5, "Lucía", "USER", 15),
                new Usuario(6, "Julian", "USER", 7),
                new Usuario(7, "Valeria", "USER", 12),
                new Usuario(8, "Fernando", "USER", 20),
                new Usuario(9, "Beatriz", "USER", 4),
                new Usuario(10, "Ricardo", "USER", 9),
                new Usuario(11, "Claudia", "USER", 50)
        );
        listarUserView.cargarLibros(listaUsuarios);
        listarUserView.show();
    }
}
