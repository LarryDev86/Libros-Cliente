package es.larry.libroscliente.controller;

import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.sesion.Sesion;
import es.larry.libroscliente.view.ModificarView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class ModificarController {

    private ModificarView modificarView;
    private LoginService service;
    private String token;

    public ModificarController(ModificarView modificarView,String token) {
        this.modificarView = modificarView;
        this.service = new LoginService();
        this.token = token;
        initEvents();
    }
    // Accion cuando pulsamos el boton de logout invoca el metodo login y se ejecuta su contenido.
    private void initEvents(){
        modificarView.getBtnEntrar().setOnAction(e -> login());
    }
    private void login(){
        //Con esto crearemos un nuevo usuario
        String usuario = modificarView.getTxtUsuer().getText();
        String nombreCompleto = modificarView.getTxtNombreCompleto().getText();
        String email = modificarView.getTxtEmail().getText();
        String password = modificarView.getTxtPassword().getText();

        try {
            int status = service.modificarUSer(usuario,nombreCompleto,email,password);

            if (status == 200) {
                modificarView.getLblMensaje().setText("Modificacion correcta");
                modificarView.getLblMensaje().setStyle("-fx-text-fill: green;");
                //Mensaje con temporizador
                PauseTransition pausa = new PauseTransition(Duration.seconds(2));
                pausa.setOnFinished(event -> modificarView.getStage().close());
                pausa.play();
            } else {
                modificarView.getLblMensaje().setText("Credenciales incorrectas");
            }

        } catch (Exception e) {
            modificarView.getLblMensaje().setText("Error al conectar con el servidor");
        }
    }
}
