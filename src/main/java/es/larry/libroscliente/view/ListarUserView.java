package es.larry.libroscliente.view;

import es.larry.libroscliente.dto.Usuario;
import es.larry.libroscliente.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ListarUserView {

    private VBox root;
    private Stage stage;
    private Label lblTitulo;
    private TableView<Usuario> tablaUsuarios;
    private TableColumn<Usuario, Integer> colId;
    private TableColumn<Usuario, String> colNombre;
    private TableColumn<Usuario, String> colRol;
    private TableColumn<Usuario, Integer> colPuntos;

    public ListarUserView() {

        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("fondo");

        crearTitulo();
        crearTablaVacia();

        root.getChildren().addAll(
                crearTopBar(),
                lblTitulo,
                tablaUsuarios
        );
        VBox.setVgrow(tablaUsuarios, Priority.ALWAYS);

        stage = new Stage();
        Scene scene = new Scene(root, 900, 600);

        UIUtils.applyMainStyle(scene);
        UIUtils.setStyleTablaLibros(scene);
        UIUtils.setAppIcon(stage);
        stage.setScene(scene);
    }

    private HBox crearTopBar() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox topBar = new HBox(spacer);
        topBar.setAlignment(Pos.CENTER);
        return topBar;
    }

    private void crearTitulo() {
        lblTitulo = new Label("Listado de usuarios");
        lblTitulo.getStyleClass().add("titulo-seccion");
    }

    private void crearTablaVacia() {
        tablaUsuarios = new TableView<>();
        tablaUsuarios.getStyleClass().add("tabla-libros");
        tablaUsuarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        colId = new TableColumn<>("ID");
        colNombre = new TableColumn<>("Nombre");
        colRol = new TableColumn<>("Rol");
        colPuntos = new TableColumn<>("Puntos");

        colId.setStyle("-fx-alignment: CENTER;");
        colNombre.setStyle("-fx-alignment: CENTER;");
        colRol.setStyle("-fx-alignment: CENTER;");
        colPuntos.setStyle("-fx-alignment: CENTER;");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colPuntos.setCellValueFactory(new PropertyValueFactory<>("puntos"));

        tablaUsuarios.getColumns().addAll(colId , colNombre , colRol , colPuntos);
    }

    public void cargarLibros(List<Usuario> listaUsuarios) {
        tablaUsuarios.getItems().clear();
        tablaUsuarios.getItems().addAll(listaUsuarios);
    }

    public TableView<Usuario> getTablaUsuarios() {
        return tablaUsuarios;
    }

    public Stage getStage() {
        return stage;
    }

    public VBox getRoot() {
        return root;
    }

    public void show(){
        stage.show();
    }
}
