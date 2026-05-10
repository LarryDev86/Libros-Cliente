package es.larry.libroscliente.controller;

import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.view.EliminarUser;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class EliminarController {

    private EliminarUser eliminarUser;
    private LoginService loginService;

    public EliminarController(EliminarUser eliminarUser) {
        this.eliminarUser = eliminarUser;
        this.loginService = new LoginService();
        initEvent();
    }

    private void initEvent(){
        eliminarUser.getBtnEliminar().setOnAction( e -> delete());

    }
    private void delete(){
        //De aqui sacamos el id para enviar al server
        int idUser = Integer.parseInt(eliminarUser.getTxtUsuer().getText());
        try{
            int status = loginService.eliminarUser(idUser);
            if(status == 200){

                eliminarUser.getLblMensaje().setText("Se elimino correctamente");
                eliminarUser.getLblMensaje().setStyle("-fx-text-fill: green;");

            }else{
                eliminarUser.getLblMensaje().setText("Error no se pudo eliminar, Usuario no encontrado");
            }
            PauseTransition pausa = new PauseTransition(Duration.seconds(2));
            pausa.setOnFinished(event -> eliminarUser.getStage().close());
            pausa.play();

        }catch(Exception e ){
            eliminarUser.getLblMensaje().setText("Error al conectar con el servidor");
        }
    }
}

