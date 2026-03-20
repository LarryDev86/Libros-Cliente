package es.larry.libroscliente.view;


import es.larry.libroscliente.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Clase que define como sera la pantalla logout
public class LogoutView {
    private VBox root;
    private Button logoutButton;
    private Stage stage;
    private String rol;

    public LogoutView() {
        root = new VBox();
        root.getStyleClass().add("fondo");
        logoutButton = new Button("Logout");
        HBox topBar = new HBox(logoutButton);
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPadding(new Insets(20, 30, 0, 0));
        root.getChildren().add(topBar);
        stage = new Stage();
        Scene scene = new Scene(root,600,400);
        UIUtils.applyMainStyle(scene);
        UIUtils.setAppIcon(stage);
        stage.setScene(scene);

    }

    public void setRol(String rol) {
        stage.setTitle("Bienvenido "+rol);
    }

    public Parent getRoot() {
        return root;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public Stage getStage() {
        return stage;
    }

    public void show() {
        stage.show();
    }
}
