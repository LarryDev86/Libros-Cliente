package es.larry.libroscliente.controller;

import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.sesion.Sesion;
import es.larry.libroscliente.view.HomeView;
import es.larry.libroscliente.view.RegistrarUserView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class RegistrarUserController {

    private RegistrarUserView registrarUserView;
    private LoginService service;
    private HomeView homeView;

    public RegistrarUserController(RegistrarUserView registrarUserView, HomeView homeView) {
        this.registrarUserView = registrarUserView;
        this.homeView = homeView;
        this.service = new LoginService();
        initEvents();
    }

    private void initEvents(){
        registrarUserView.getBtnRegistro().setOnAction(e -> nuevoRegistro());
    }

    private void nuevoRegistro(){
        //Con esto crearemos un nuevo usuario
        String usuario = registrarUserView.getTxtUsuer().getText();
        String nombreCompleto = registrarUserView.getTxtNombreCompleto().getText();
        String email = registrarUserView.getTxtEmail().getText();
        String password = registrarUserView.getTxtPassword().getText();

        try {
            String token = service.registroUSer(usuario,nombreCompleto,email,password);

            if (token != null && !token.isBlank()) {
                registrarUserView.getLblMensaje().setText("Registro correcto");
                registrarUserView.getLblMensaje().setStyle("-fx-text-fill: green;");
                //Mensaje con temporizador
                PauseTransition pausa = new PauseTransition(Duration.seconds(2));
                pausa.setOnFinished(event -> registrarUserView.getStage().close());
                pausa.play();
            } else {
                registrarUserView.getLblMensaje().setText("Credenciales incorrectas");
            }

        } catch (Exception e) {
            registrarUserView.getLblMensaje().setText("Error al conectar con el servidor");
        }
    }
}
