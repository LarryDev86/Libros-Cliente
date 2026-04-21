package es.larry.libroscliente.view;

import es.larry.libroscliente.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Clase que define como sera la pantalla logout
public class LogoutView {
    private VBox root;
    private MenuButton mbMenu;
    private MenuItem menuModificar;
    private MenuItem menuRanking;
    private MenuItem menuListarLibros;
    private MenuItem menuDatosUser;
    private MenuItem menuLogOut;
    private Stage stage;
    private String rol;

    public LogoutView() {
        root = new VBox();
        root.getStyleClass().add("fondo");
        // Menu desplegable (ahora como botón)
        mbMenu = new MenuButton(rol);
        mbMenu.getStyleClass().add("menu-usuario");
        mbMenu.setPrefWidth(90);
        mbMenu.setPrefHeight(24);
        menuModificar = new MenuItem("Modificar");
        menuListarLibros = new MenuItem("Listar Libros");
        menuRanking = new MenuItem("Ranking");
        menuDatosUser = new MenuItem("Datos User");
        menuLogOut = new MenuItem("LogOut");
        // Añadir items al botón
        mbMenu.getItems().addAll(
                menuModificar,
                menuListarLibros,
                menuRanking,
                menuDatosUser,
                menuLogOut
        );
        HBox topBar = new HBox(mbMenu);
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPadding(new Insets(20, 30, 0, 0));
        root.getChildren().add(topBar);
        stage = new Stage();
        stage.setTitle("Bienvenido");
        Scene scene = new Scene(root,700,500);
        // Cargar CSS
        UIUtils.applyMainStyle(scene);
        UIUtils.setStyleMenu(scene);
        UIUtils.setAppIcon(stage);
        stage.setScene(scene);

    }

    public void setRol(String rol) {
        this.rol = rol;
        mbMenu.setText(rol);
    }
    public Parent getRoot() {
        return root;
    }

    public MenuItem getMenuModificar() {
        return menuModificar;
    }

    public MenuItem getMenuRanking() {
        return menuRanking;
    }

    public MenuItem getMenuListarLibros() {
        return menuListarLibros;
    }

    public MenuItem getMenuDatosUser() {
        return menuDatosUser;
    }

    public MenuItem getMenuLogOut() {
        return menuLogOut;
    }

    public Stage getStage() {
        return stage;
    }

    public void show() {
        stage.show();
    }
}
