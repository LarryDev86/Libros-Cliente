package es.larry.libroscliente.view;

import es.larry.libroscliente.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// Clase que define como sera la pantalla home
public class HomeView {
    private VBox root;
    private Button loginButton;
    private Stage stage;

    public HomeView(Stage stage) {
        this.stage = stage;
        root = new VBox();
        root.getStyleClass().add("fondo");
        loginButton = new Button("Login / Registrarte");
        HBox topBar = new HBox(loginButton);
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setPadding(new Insets(20));
        root.getChildren().add(topBar);
        Scene scene = new Scene(root, 700, 500);
        UIUtils.applyMainStyle(scene);
        UIUtils.setAppIcon(stage);

        stage.setScene(scene);
        stage.setTitle("Libro Retos");
    }
    public void show(){
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public Parent getRoot() {
        return root;
    }

    public Button getLogionButton() {
        return loginButton;
    }
}
