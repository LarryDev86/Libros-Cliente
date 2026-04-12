package es.larry.libroscliente.view;

import es.larry.libroscliente.dto.LibroFila;
import es.larry.libroscliente.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ListaLibrosView {

    private VBox root;
    private Stage stage;

    private Label lblTitulo;

    private TableView<LibroFila> tablaLibros;
    private TableColumn<LibroFila, Integer> colId;
    private TableColumn<LibroFila, String> colTitulo;
    private TableColumn<LibroFila, String> colAutor;
    private TableColumn<LibroFila, String> colGenero;
    private TableColumn<LibroFila, Boolean> colDisponi;

    public ListaLibrosView() {
        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("fondo");

        crearTitulo();
        crearTablaVacia();

        root.getChildren().addAll(
                crearTopBar(),
                lblTitulo,
                tablaLibros
        );

        VBox.setVgrow(tablaLibros, Priority.ALWAYS);

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
        lblTitulo = new Label("Listado de libros");
        lblTitulo.getStyleClass().add("titulo-seccion");
    }

    private void crearTablaVacia() {
        tablaLibros = new TableView<>();
        tablaLibros.getStyleClass().add("tabla-libros");
        tablaLibros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        colId = new TableColumn<>("ID");
        colTitulo = new TableColumn<>("Título");
        colAutor = new TableColumn<>("Autor");
        colGenero = new TableColumn<>("Categoria");
        colDisponi = new TableColumn<>("Disponibilidad");

        colId.setStyle("-fx-alignment: CENTER;");
        colTitulo.setStyle("-fx-alignment: CENTER;");
        colAutor.setStyle("-fx-alignment: CENTER;");
        colGenero.setStyle("-fx-alignment: CENTER;");
        colDisponi.setStyle("-fx-alignment: CENTER;");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colDisponi.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        colDisponi.setCellFactory(column -> new TableCell<LibroFila, Boolean>() {
            @Override
            protected void updateItem(Boolean disponible, boolean empty) {
                super.updateItem(disponible, empty);

                setAlignment(Pos.CENTER);

                if (empty || disponible == null) {
                    setText(null);
                    setStyle("-fx-alignment: CENTER;");
                } else if (disponible) {
                    setText("Disponible");
                    setStyle("-fx-alignment: CENTER; -fx-text-fill: green; -fx-font-weight: bold;");
                } else {
                    setText("No disponible");
                    setStyle("-fx-alignment: CENTER; -fx-text-fill: red; -fx-font-weight: bold;");
                }
            }
        });

        tablaLibros.getColumns().addAll(colId, colTitulo, colAutor, colGenero, colDisponi);
    }

    public void cargarLibros(List<LibroFila> listaLibros) {
        tablaLibros.getItems().clear();
        tablaLibros.getItems().addAll(listaLibros);
    }

    public Parent getView() {
        return root;
    }

    public Stage getStage() {
        return stage;
    }

    public TableView<LibroFila> getTablaLibros() {
        return tablaLibros;
    }

    public void show() {
        stage.show();
    }
}
