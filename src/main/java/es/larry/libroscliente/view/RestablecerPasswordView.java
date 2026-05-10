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

public class RestablecerPasswordView {

    private Stage stage;
    private TextField txtUsuer;
    private PasswordField txtPassword;
    private Button btnRestablecer;
    private Label lblMensaje;

    // Aqui definimos como será la ventana de login del usuario
    public RestablecerPasswordView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Restablecer contraseña usuario");
        stage.setWidth(400);
        stage.setHeight(350);
        Label lblUser = new Label("Id del usuario: ");
        lblUser.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtUsuer = new TextField();
        Label lblPass = new Label("Nueva contraseña: ");
        lblPass.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtPassword = new PasswordField();
        txtUsuer.setPrefWidth(280);
        txtPassword.setPrefWidth(280);
        btnRestablecer = new Button("Restablecer");
        int ancho = 100;
        btnRestablecer.setPrefWidth(ancho);
        // Opcion para desactivar el boton, hasta no rellenar los dos campos
        BooleanBinding camposVacios = txtUsuer.textProperty().isEmpty();
        btnRestablecer.disableProperty().bind(camposVacios);
        lblMensaje = new Label();
        lblMensaje.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
        VBox root = new VBox(14,lblUser,txtUsuer,lblPass,txtPassword
                ,btnRestablecer,lblMensaje);
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

    public Label getLblMensaje() {
        return lblMensaje;
    }

    public Button getBtnRestablecer() {
        return btnRestablecer;
    }

    public TextField getTxtUsuer() {
        return txtUsuer;
    }

    public Stage getStage() {
        return stage;
    }

    public TextField getTxtPassword() {
        return txtPassword;
    }
}
