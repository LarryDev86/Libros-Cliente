package es.larry.libroscliente.controller;

import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.view.EliminarLibroView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class EliminarLibroContrller {

    private EliminarLibroView eliminarLibroView;
    private LoginService loginService;

    public EliminarLibroContrller(EliminarLibroView eliminarLibroView) {
        this.eliminarLibroView = eliminarLibroView;
        this.loginService = new LoginService();
        initEvent();
    }

    private void initEvent(){
        eliminarLibroView.getBtnEliminar().setOnAction( e -> eliminarLibro());

    }

    private void eliminarLibro(){

        int idLibro = Integer.parseInt(eliminarLibroView.getTxtIdLibro().getText());

        try{
            int status = loginService.borrarLibro(idLibro);
            if(status == 200){
                eliminarLibroView.getLblMensaje().setText("Eliminado correctamente");
                eliminarLibroView.getLblMensaje().setStyle("-fx-text-fill: green;");
            }else{
                eliminarLibroView.getLblMensaje().setText("No se pudo eliminar, Libro no encontrado");
            }
            PauseTransition pausa = new PauseTransition(Duration.seconds(2));
            pausa.setOnFinished(event -> eliminarLibroView.getStage().close());
            pausa.play();
        }catch(Exception e ){
            eliminarLibroView.getLblMensaje().setText("Error al conectar con el servidor");
        }
    }
}
