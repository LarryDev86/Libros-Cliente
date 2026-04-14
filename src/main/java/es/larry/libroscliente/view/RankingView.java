package es.larry.libroscliente.view;

import es.larry.libroscliente.dto.Ranking;
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

public class RankingView {

    private VBox root;
    private Stage stage;
    private Label lblTitulo;
    private TableView<Ranking> tablaRanking;
    private TableColumn<Ranking, String> colNomUser;
    private TableColumn<Ranking, Integer> colPuntosUser;

    public RankingView() {

        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("fondo");
        crearTitulo();
        crearTablaVacia();
        root.getChildren().addAll(
                crearTopBar(),
                lblTitulo,
                tablaRanking
        );
        VBox.setVgrow(tablaRanking, Priority.ALWAYS);
        stage = new Stage();
        Scene scene = new Scene(root, 900, 600);
        UIUtils.applyMainStyle(scene);
        UIUtils.setStyleTablaLibros(scene);
        UIUtils.setAppIcon(stage);
        stage.setScene(scene);
    }
    private HBox crearTopBar() {
        Region spacer = new Region();
        javafx.scene.layout.HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox topBar = new HBox(spacer);
        topBar.setAlignment(Pos.CENTER);
        return topBar;
    }

    private void crearTitulo() {
        lblTitulo = new Label("Ranking de puntos");
        lblTitulo.getStyleClass().add("titulo-seccion");
    }
    private void crearTablaVacia() {
        tablaRanking = new TableView<>();
        tablaRanking.getStyleClass().add("tabla-ranking");
        tablaRanking.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        colNomUser = new TableColumn<>("Nombre");
        colPuntosUser = new TableColumn<>("Puntos");
        colNomUser.setStyle("-fx-alignment: CENTER;");
        colPuntosUser.setStyle("-fx-alignment: CENTER;");
        colNomUser.setCellValueFactory(new PropertyValueFactory<>("nombreUser"));
        colPuntosUser.setCellValueFactory(new PropertyValueFactory<>("puntos"));

        tablaRanking.getColumns().addAll(colNomUser,colPuntosUser);
    }

    public void cargarTabla(List<Ranking> lRanking){
        tablaRanking.getItems().clear();
        tablaRanking.getItems().addAll(lRanking);
    }

    public void show(){
        stage.show();
    }

    public VBox getRoot() {
        return root;
    }

    public Stage getStage() {
        return stage;
    }

    public Label getLblTitulo() {
        return lblTitulo;
    }
}
