package es.larry.libroscliente.controller;

import es.larry.libroscliente.dto.Usuario;
import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.sesion.Sesion;
import es.larry.libroscliente.view.*;

import java.util.List;

public class AdminController {

    private AdminView adminView;
    private HomeView homeView;
    private LoginService loginService;

    public AdminController(AdminView adminView, HomeView homeView) {
        this.adminView = adminView;
        this.homeView = homeView;
        this.loginService = new LoginService();
        initEvents();
    }

    private void initEvents() {
        adminView.getMenuListarUsers().setOnAction(e -> listarUsers());
        adminView.getMenuInsertaLibro().setOnAction(e -> insertarLibro());
        adminView.getMenuEliminarLibro().setOnAction(e -> EliminarLibro());
        adminView.getMenuListarLibro().setOnAction(e -> listarLibro());
        adminView.getMenuEliminarUser().setOnAction(e -> EliminiarUser());
        adminView.getMenuCrearUser().setOnAction(e -> crearUser());
        adminView.getMenuRestablecerPassword().setOnAction(e -> restablecerPassword());
        adminView.getMenuLogOut().setOnAction(e -> logout());
    }

    private void logout() {
        adminView.getStage().close();
        Sesion.cleanToken();
        homeView.show();
    }
    private void listarLibro(){
        LogoutController lg = new LogoutController();
        lg.listarLibros();
    }
    private void crearUser(){
        RegistrarUserView user = new RegistrarUserView();
        new RegistrarUserController(user);
        user.show();
    }
    private void listarUsers() {
        try {
            List<Usuario> usuarios = loginService.listarUsuariosAdmin();

            ListarUserView listarUserView = new ListarUserView();
            listarUserView.cargarUsuarios(usuarios);
            listarUserView.show();
            listarUserView.getVolverBtn().setOnAction( e -> {
                //Cerramos la venta
                listarUserView.getStage().close();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertarLibro() {
        InsertarLibroView insert = new InsertarLibroView();
        new InsertarLibroController(insert);
        insert.show();
    }

    private void EliminarLibro() {

        EliminarLibroView delete = new EliminarLibroView();
        new EliminarLibroContrller(delete);
        delete.show();
    }

    private void EliminiarUser() {
        EliminarUser delete = new EliminarUser();
        new EliminarController(delete);
        delete.show();
    }

    private void restablecerPassword() {

        RestablecerPasswordView reset = new RestablecerPasswordView();
        new RestablecerPasswordController(reset);
        reset.show();
    }
}