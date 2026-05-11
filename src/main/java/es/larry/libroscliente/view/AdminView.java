package es.larry.libroscliente.view;

import es.larry.libroscliente.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminView {

    private VBox root;
    private MenuButton mbMenu;
    private MenuItem menuListarUsers;
    private MenuItem menuInsertaLibro;
    private MenuItem menuEliminarLibro;
    private MenuItem menuListarLibro;
    private MenuItem menuEliminarUser;
    private MenuItem menuRestablecerPassword;
    private MenuItem menuRanking;
    private MenuItem menuCrearUser;
    private MenuItem menuLogOut;
    private Stage stage;
    private String rol;


    public AdminView() {
        root = new VBox();
        root.getStyleClass().add("fondo");
        // Menu desplegable (ahora como botón)
        mbMenu = new MenuButton(rol);
        mbMenu.getStyleClass().add("menu-usuario");
        mbMenu.setPrefWidth(90);
        mbMenu.setPrefHeight(24);
        menuListarUsers = new MenuItem("Listar users");
        menuInsertaLibro = new MenuItem("Insert libro");
        menuEliminarLibro = new MenuItem("Eliminar libro");
        menuListarLibro = new MenuItem("Listar libro");
        menuEliminarUser = new MenuItem("Eliminar user");
        menuRanking = new MenuItem("Ranking de puntos");
        menuCrearUser = new MenuItem("Crear Usuario");
        menuRestablecerPassword = new MenuItem("Restablecer password");
        menuLogOut = new MenuItem("LogOut");
        // Añadir items al botón
        mbMenu.getItems().addAll(
                menuListarUsers,
                menuEliminarUser,
                menuCrearUser,
                menuRestablecerPassword,
                menuListarLibro,
                menuInsertaLibro,
                menuEliminarLibro,
                menuRanking,
                menuLogOut
        );
        HBox topBar = new HBox(mbMenu);
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPadding(new Insets(10, 15, 10, 15)); //pading para el boton flecha
        root.getChildren().add(topBar);
        stage = new Stage();
        stage.setTitle("Bienvenido - Sesion Administrador");
        Scene scene = new Scene(root,700,500);
        // Cargar CSS
        UIUtils.applyMainStyle(scene);
        UIUtils.setStyleMenu(scene);
        UIUtils.setAppIcon(stage);
        stage.setScene(scene);
    }

    public void setRol() {
        mbMenu.setText("ADMIN");
    }

    public MenuItem getMenuRanking() {
        return menuRanking;
    }

    public VBox getRoot() {
        return root;
    }
    public MenuButton getMbMenu() {
        return mbMenu;
    }

    public MenuItem getMenuListarUsers() {
        return menuListarUsers;
    }

    public MenuItem getMenuInsertaLibro() {
        return menuInsertaLibro;
    }

    public MenuItem getMenuEliminarLibro() {
        return menuEliminarLibro;
    }

    public MenuItem getMenuEliminarUser() {
        return menuEliminarUser;
    }

    public MenuItem getMenuRestablecerPassword() {
        return menuRestablecerPassword;
    }

    public MenuItem getMenuLogOut() {
        return menuLogOut;
    }

    public MenuItem getMenuListarLibro() {
        return menuListarLibro;
    }

    public MenuItem getMenuCrearUser() {
        return menuCrearUser;
    }

    public Stage getStage() {
        return stage;
    }

    public void show() {
        stage.show();
    }
}
