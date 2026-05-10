package es.larry.libroscliente.controller;

import es.larry.libroscliente.dto.LibroFila;
import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.view.InsertarLibroView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class InsertarLibroController {

    private InsertarLibroView insertarLibroView;
    private LoginService loginService;

    public InsertarLibroController(InsertarLibroView insertarLibroView) {
        this.insertarLibroView = insertarLibroView;
        this.loginService = new LoginService();
        initEvent();
    }

    private void initEvent(){
        insertarLibroView.getBtnInsertar().setOnAction( e -> insertar());
    }

    private void insertar(){
        String titulo = insertarLibroView.getTxtTitulo().getText();
        String autor = insertarLibroView.getTxtAutor().getText();
        String editorial = insertarLibroView.getTxtEditorial().getText();

        try{

            int status = loginService.insertarLibro(new LibroFila(titulo,autor,editorial));

            if(status == 200){
                insertarLibroView.getLblMensaje().setText("Insertado correctamente");
                insertarLibroView.getLblMensaje().setStyle("-fx-text-fill: green;");

            }else{
                insertarLibroView.getLblMensaje().setText("No se pudo insertar");
            }
            //Mensaje con temporizador
            PauseTransition pausa = new PauseTransition(Duration.seconds(2));
            pausa.setOnFinished(event -> insertarLibroView.getStage().close());
            pausa.play();
        }catch(Exception e){
            insertarLibroView.getLblMensaje().setText("Error al conectar con el servidor");
        }
    }
}
