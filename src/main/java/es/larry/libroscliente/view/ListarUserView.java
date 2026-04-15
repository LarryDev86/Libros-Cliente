package es.larry.libroscliente.view;

import es.larry.libroscliente.dto.RequestDtoRegistro;
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

        colId = new TableColumn<>("Usuario");
        colNombre = new TableColumn<>("Nombre completo");
        colRol = new TableColumn<>("Email");
        colPuntos = new TableColumn<>("Puntos");

        colId.setStyle("-fx-alignment: CENTER;");
        colNombre.setStyle("-fx-alignment: CENTER;");
        colRol.setStyle("-fx-alignment: CENTER;");
        colPuntos.setStyle("-fx-alignment: CENTER;");

        colId.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPuntos.setCellValueFactory(new PropertyValueFactory<>("puntos"));

        tablaUsuarios.getColumns().addAll(colId , colNombre , colRol , colPuntos);
    }
    private void ajustarAlturaTabla(int numFilas) {
        double alturaCabecera = 40;
        double alturaFila = 40;
        double paddingExtra = 15;

        tablaUsuarios.setFixedCellSize(alturaFila);
        tablaUsuarios.prefHeightProperty().bind(
                tablaUsuarios.fixedCellSizeProperty().multiply(numFilas)
                        .add(alturaCabecera)
                        .add(paddingExtra)
        );
        tablaUsuarios.setMaxHeight(TableView.USE_PREF_SIZE);
    }
    public void cargarLibros(Usuario usuario) {
        tablaUsuarios.getItems().clear();
        tablaUsuarios.getItems().addAll(usuario);
        ajustarAlturaTabla(1);
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
