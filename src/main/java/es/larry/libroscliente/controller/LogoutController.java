package es.larry.libroscliente.controller;

import es.larry.libroscliente.sesion.Sesion;
import es.larry.libroscliente.view.*;

// Clase donde implementa la ventana de logout ( sesion del del usuario )
public class LogoutController {

    private LogoutView logoutView;
    private HomeView homeView;

    public LogoutController(LogoutView logoutView, HomeView homeView) {
        this.logoutView = logoutView;
        this.homeView = homeView;
        initEvents();
    }

    // Con este bton estamos entrando dentro del metodo logout y con ellos enviandolo a la pantalla home
    private void initEvents(){
        logoutView.getMenuLogOut().setOnAction(e -> logout());
        logoutView.getMenuListarLibros().setOnAction(e -> listarLibros());
        logoutView.getMenuDatosUser().setOnAction(e -> listarUsers());
        logoutView.getMenuModificar().setOnAction(e -> modificarUser());
        logoutView.getMenuRanking().setOnAction(e -> rankingUser());
    }

    private void logout(){
        //Cerramos la venta
        logoutView.getStage().close();
        // Dejamos el token vacio cuando pulsen el logout
        Sesion.cleanToken();
        //Volvemos a la pantalla inicial
        homeView.show();
    }
    private void listarLibros(){
        ListaLibrosController listarLibros = new ListaLibrosController(
                new ListaLibrosView()
        );
    }

    private void listarUsers(){
        ListaUsersController lista = new ListaUsersController(
                new ListarUserView()
        );
    }

    private void modificarUser(){
        ModificarView modi = new ModificarView();
        new ModificarController(modi);
        modi.show();
    }
    private void rankingUser(){
        RankingController rk = new RankingController(
                new RankingView()
        );
    }
}
