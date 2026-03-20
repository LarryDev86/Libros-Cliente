package es.larry.libroscliente;

import es.larry.libroscliente.controller.HomeController;
import es.larry.libroscliente.view.HomeView;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static void main(String[]args){
        launch(args);
    }

    // Aqui es donde apuntamos a la primera pantalla
    @Override
    public void start(Stage stage) throws Exception {
        HomeView homeView = new HomeView(stage);
        new HomeController(homeView);
        homeView.show();
    }
}
