package es.larry.libroscliente.view;

import es.larry.libroscliente.utils.UIUtils;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EliminarLibroView {

    private Stage stage;
    private TextField txtIdLibro;
    private Button btnEliminar;
    private Label lblMensaje;

    // Aqui definimos como será la ventana de login del usuario
    public EliminarLibroView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Eliminar Usuario");
        stage.setWidth(400);
        stage.setHeight(350);
        Label lblLibro = new Label("Id del Libro: ");
        lblLibro.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtIdLibro = new TextField();
        txtIdLibro.setPrefWidth(280);
        btnEliminar = new Button("Eliminar");
        int ancho = 100;
        btnEliminar.setPrefWidth(ancho);
        // Opcion para desactivar el boton, hasta no rellenar los dos campos
        BooleanBinding camposVacios = txtIdLibro.textProperty().isEmpty();
        btnEliminar.disableProperty().bind(camposVacios);
        lblMensaje = new Label();
        lblMensaje.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
        VBox root = new VBox(14,lblLibro,txtIdLibro
                ,btnEliminar,lblMensaje);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-background-color: #f4f4f4;");
        Scene scene = new Scene(root);
        // Con esto añadimos el logo
        UIUtils.setAppIcon(stage);
        stage.setScene(scene);
    }
    public void show(){
        stage.showAndWait();
    }

    public Stage getStage() {
        return stage;
    }

    public TextField getTxtIdLibro() {
        return txtIdLibro;
    }

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public Label getLblMensaje() {
        return lblMensaje;
    }
}
