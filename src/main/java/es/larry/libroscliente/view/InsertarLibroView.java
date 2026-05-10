package es.larry.libroscliente.view;

import es.larry.libroscliente.utils.UIUtils;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InsertarLibroView {

    private Stage stage;
    private TextField txtTitulo;
    private TextField txtAutor;
    private TextField txtEditorial;
    private Button btnInsertar;
    private Label lblMensaje;

    // Aqui definimos como será la ventana de login del usuario
    public InsertarLibroView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Insertar nuevo libro");
        stage.setWidth(400);
        stage.setHeight(350);
        Label lblTitulo = new Label("Titulo: ");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtTitulo = new TextField();
        txtTitulo.setPrefWidth(280);
        Label lblAutor = new Label("Autor:");
        lblAutor.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtAutor = new TextField();
        txtAutor.setPrefWidth(280);
        Label lblEditorial = new Label("Editorial:");
        lblEditorial.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtEditorial = new TextField();
        txtEditorial.setPrefWidth(280);
        btnInsertar = new Button("Insertar");
        //Definimos mismo tamaña para los 3 botones
        int ancho = 100;
        btnInsertar.setPrefWidth(ancho);
        // Opcion para desactivar el boton, hasta no rellenar los dos campos
        BooleanBinding camposVacios =
                txtTitulo.textProperty().isEmpty()
                .or(txtAutor.textProperty().isEmpty())
                .or(txtEditorial.textProperty().isEmpty());
        btnInsertar.disableProperty().bind(camposVacios);
        lblMensaje = new Label();
        lblMensaje.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
        VBox root = new VBox(14,lblTitulo,txtTitulo,
                lblAutor,txtAutor,lblEditorial,txtEditorial
                ,btnInsertar,lblMensaje);
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

    public TextField getTxtTitulo() {
        return txtTitulo;
    }

    public TextField getTxtAutor() {
        return txtAutor;
    }

    public TextField getTxtEditorial() {
        return txtEditorial;
    }

    public Button getBtnInsertar() {
        return btnInsertar;
    }

    public Label getLblMensaje() {
        return lblMensaje;
    }
}
