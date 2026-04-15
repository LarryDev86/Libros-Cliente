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

public class RegistrarUserView {
    private Stage stage;
    private TextField txtUsuer;
    private TextField txtNombreCompleto;
    private TextField txtEmail;
    private PasswordField txtPassword;
    private Button btnRegistro;
    private Label lblMensaje;

    // Aqui definimos como será la ventana de login del usuario
    public RegistrarUserView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Registrarte");
        stage.setWidth(500);
        stage.setHeight(500);
        Label lblUser = new Label("Usuario: ");
        lblUser.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtUsuer = new TextField();
        txtUsuer.setPrefWidth(280);
        //Nombre completo
        Label lblNombreCompleto = new Label("Nombre completo: ");
        lblNombreCompleto.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtNombreCompleto = new TextField();
        txtNombreCompleto.setPrefWidth(280);
        //Email
        Label lblEmail = new Label("Email: ");
        lblEmail.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtEmail = new TextField();
        txtEmail.setPrefWidth(280);
        Label lblPassword = new Label("Contraseña:");
        lblPassword.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtPassword = new PasswordField();
        txtPassword.setPrefWidth(280);
        btnRegistro = new Button("Registrate");
        //Definimos mismo tamaña para los 3 botones
        int ancho = 100;
        btnRegistro.setPrefWidth(ancho);
        // Opcion para desactivar el boton, hasta no rellenar los dos campos
        BooleanBinding camposVacios = txtUsuer.textProperty().isEmpty()
                .or(txtPassword.textProperty().isEmpty());
        btnRegistro.disableProperty().bind(camposVacios);
        lblMensaje = new Label();
        lblMensaje.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
        VBox root = new VBox(14,lblUser,txtUsuer,lblNombreCompleto,txtNombreCompleto,lblEmail,txtEmail,
                lblPassword,txtPassword
                ,btnRegistro,lblMensaje);
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

    public Label getLblMensaje() {
        return lblMensaje;
    }

    public TextField getTxtNombreCompleto() {
        return txtNombreCompleto;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public Button getBtnRegistro() {
        return btnRegistro;
    }

    public PasswordField getTxtPassword() {
        return txtPassword;
    }

    public TextField getTxtUsuer() {
        return txtUsuer;
    }

}
