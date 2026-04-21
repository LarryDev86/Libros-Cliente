package es.larry.libroscliente.controller;

import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.sesion.Sesion;
import es.larry.libroscliente.view.HomeView;
import es.larry.libroscliente.view.LoginView;
import es.larry.libroscliente.view.LogoutView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/* Clase implementa lo que sera la pantalla donde logearse el usuario y donde
* enviar los datos a la capa de servicio y le traera aqui la respuesta del servidor
* un token o un null.
* */
public class LoginController {

    private LoginView loginView;
    private LoginService service;
    private HomeView homeView;

    public LoginController(LoginView loginView,HomeView homeView) {
        this.loginView = loginView;
        this.homeView = homeView;
        this.service = new LoginService();
        initEvents();
    }
    // Accion cuando pulsamos el boton de logout invoca el metodo login y se ejecuta su contenido.
    private void initEvents(){

        loginView.getBtnEntrar().setOnAction(e -> login());
        loginView.getBtnBaja().setOnAction(e -> bajaUser());
    }
    private void login(){
        String nombre = loginView.getTxtUsuer().getText();
        String password = loginView.getTxtPassword().getText();

        try {
            String token = service.login(nombre, password);
            if (token != null && !token.isBlank()) {
                //Guardamos el token en memoria
                Sesion.setToken(token);
                //Aqui cerramos las dos ventanas.
                homeView.getStage().close();
                loginView.getStage().close();
                LogoutView lg = new LogoutView();
                lg.setRol(nombre);
                new LogoutController(lg,homeView,token);
                lg.show();
            } else {
                loginView.getLblMensaje().setText("Credenciales incorrectas");
            }

        } catch (Exception e) {
            loginView.getLblMensaje().setText("Error al conectar con el servidor");
        }
    }

    private void bajaUser(){
        String nombre = loginView.getTxtUsuer().getText();
        String password = loginView.getTxtPassword().getText();

        try {
            String token = service.login(nombre, password);
            if (token != null && !token.isBlank()) {
                //Sesion.setToken(token);
                int status = service.bajaUser(token);
                if(status == 200) {
                    loginView.getLblMensaje().setText("Eliminado correctamente");
                    loginView.getLblMensaje().setStyle("-fx-text-fill: green;");
                }else if(status == 500){
                    loginView.getLblMensaje().setText("Fallo!! Tiene retos pendientes");
                    loginView.getLblMensaje().setStyle("-fx-text-fill: red;");
                }
                //Mensaje con temporizador
                PauseTransition pausa = new PauseTransition(Duration.seconds(2));
                pausa.setOnFinished(event -> loginView.getStage().close());
                pausa.play();
            } else {
                loginView.getLblMensaje().setText("Credenciales incorrectas");
            }

        } catch (Exception e) {
            loginView.getLblMensaje().setText("Error al conectar con el servidor");
        }
    }
}
