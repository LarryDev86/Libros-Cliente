package es.larry.libroscliente.controller;

import es.larry.libroscliente.view.HomeView;
import es.larry.libroscliente.view.LoginView;
import es.larry.libroscliente.view.RegistrarUserView;

// Clase de la pantalla principal o pantalla home implementa la clase HomeView que es la que lo define
public class HomeController {

    private HomeView homeView;

    public HomeController(HomeView homeView) {
        this.homeView = homeView;
        initEvents();
    }
    // Con este boton se invoca el metodo openLoginWindows() y con ello desplegar la ventana de loginUser
    private void initEvents(){

        homeView.getLogionButton().setOnAction(e -> openLoginWindows());
        homeView.getBtnregistro().setOnAction(e -> registroUser());
    }

    private void openLoginWindows(){
        LoginView loginView = new LoginView();
        new LoginController(loginView,homeView);
        loginView.show();
    }
    private void registroUser(){
        RegistrarUserView rg = new RegistrarUserView();
        new RegistrarUserController(rg,homeView);
        rg.show();
    }
}
