package es.larry.libroscliente.controller;

import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.view.RestablecerPasswordView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class RestablecerPasswordController {

    private RestablecerPasswordView restablecerPasswordView;
    private LoginService loginService;

    public RestablecerPasswordController(RestablecerPasswordView restablecerPasswordView) {
        this.restablecerPasswordView = restablecerPasswordView;
        this.loginService = new LoginService();
        initEvent();
    }

    private void initEvent(){
        restablecerPasswordView.getBtnRestablecer().setOnAction( e -> restablecer());
    }

    private void restablecer(){

        int id = Integer.parseInt(restablecerPasswordView.getTxtUsuer().getText());
        String password = restablecerPasswordView.getTxtPassword().getText();
        try{
            int status = loginService.restablecer(id,password);
            if(status == 200){
                restablecerPasswordView.getLblMensaje().setText("Se restablecio correctamente");
                restablecerPasswordView.getLblMensaje().setStyle("-fx-text-fill: green;");
                PauseTransition pausa = new PauseTransition(Duration.seconds(2));
                pausa.setOnFinished(event -> restablecerPasswordView.getStage().close());
                pausa.play();
            }else{
                restablecerPasswordView.getLblMensaje().setText("Error no se pudo restablecer la contraseña");
            }

        }catch(Exception e ){
            restablecerPasswordView.getLblMensaje().setText("Error al conectar con el servidor");
        }
    }
}
