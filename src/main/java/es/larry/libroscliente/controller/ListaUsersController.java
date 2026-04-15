package es.larry.libroscliente.controller;

import es.larry.libroscliente.dto.Usuario;
import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.view.ListarUserView;

import java.util.List;

public class ListaUsersController {

    private ListarUserView listarUserView;
    private LoginService service;
    public ListaUsersController(ListarUserView listarUserView) {
        this.listarUserView = listarUserView;
        service = new LoginService();
        initEvent();
    }

    private void initEvent(){
        listarUserView.cargarLibros(service.datosUsuario());
        listarUserView.show();
    }
}
