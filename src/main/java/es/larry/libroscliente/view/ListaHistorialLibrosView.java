package es.larry.libroscliente.view;

import es.larry.libroscliente.dto.HistorialLibrosUser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.Date;
import java.util.List;

public class ListaHistorialLibrosView {

    private VBox root;

    private Label lblTituloH;

    private TableView<HistorialLibrosUser> tablaLibrosH;
    private TableColumn<HistorialLibrosUser, Integer> colIdH;
    private TableColumn<HistorialLibrosUser, String> colTituloH;
    private TableColumn<HistorialLibrosUser, String> colAutorH;
    private TableColumn<HistorialLibrosUser, Date> colFechaH;
    private TableColumn<HistorialLibrosUser, String> colEstadoH;

    public ListaHistorialLibrosView() {
        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("fondo");

        crearTitulo();
        crearTablaVacia();

        root.getChildren().addAll(
                crearTopBar(),
                lblTituloH,
                tablaLibrosH
        );

        VBox.setVgrow(tablaLibrosH, Priority.ALWAYS);
    }

    private HBox crearTopBar() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox topBar = new HBox(spacer);
        topBar.setAlignment(Pos.CENTER);
        return topBar;
    }

    private void crearTitulo() {
        lblTituloH = new Label("Historial Reservas");
        lblTituloH.getStyleClass().add("titulo-seccion");
    }

    private void crearTablaVacia() {
        tablaLibrosH = new TableView<>();
        tablaLibrosH.getStyleClass().add("tabla-reserva");
        tablaLibrosH.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        colIdH = new TableColumn<>("ID");
        colTituloH = new TableColumn<>("Título");
        colAutorH = new TableColumn<>("Autor");
        colFechaH = new TableColumn<>("Fecha");
        colEstadoH = new TableColumn<>("Estado");

        colIdH.setStyle("-fx-alignment: CENTER;");
        colTituloH.setStyle("-fx-alignment: CENTER;");
        colAutorH.setStyle("-fx-alignment: CENTER;");
        colFechaH.setStyle("-fx-alignment: CENTER;");
        colEstadoH.setStyle("-fx-alignment: CENTER;");

        colIdH.setCellValueFactory(new PropertyValueFactory<>("idLibro"));
        colTituloH.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutorH.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colFechaH.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstadoH.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tablaLibrosH.getColumns().addAll(colIdH, colTituloH, colAutorH, colFechaH, colEstadoH);
    }

    public void cargarLibros(List<HistorialLibrosUser> listaLibros) {
        tablaLibrosH.getItems().clear();
        tablaLibrosH.getItems().addAll(listaLibros);
    }

    public VBox getRoot() {
        return root;
    }

    public TableView<HistorialLibrosUser> getTablaLibros() {
        return tablaLibrosH;
    }
}