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

public class ModificarView {
    private Stage stage;
    private TextField txtUsuer;
    private PasswordField txtPassword;
    private Button btnEntrar;
    private Label lblMensaje;

    // Aqui definimos como será la ventana de login del usuario
    public ModificarView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Modificar campos");
        stage.setWidth(400);
        stage.setHeight(350);
        Label lblUser = new Label("Usuario: ");
        lblUser.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtUsuer = new TextField();
        txtUsuer.setPrefWidth(280);
        Label lblPassword = new Label("Contraseña:");
        lblPassword.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtPassword = new PasswordField();
        txtPassword.setPrefWidth(280);
        btnEntrar = new Button("Modificar");
        // Opcion para desactivar el boton, hasta no rellenar los dos campos
        BooleanBinding camposVacios = txtUsuer.textProperty().isEmpty()
                .or(txtPassword.textProperty().isEmpty());
        btnEntrar.disableProperty().bind(camposVacios);
        lblMensaje = new Label(); //Esto habra que adaptarlo a los mensajes que debe devolver
        lblMensaje.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
        VBox root = new VBox(14,lblUser,txtUsuer,
                lblPassword,txtPassword
                ,btnEntrar,lblMensaje);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-background-color: #f4f4f4;");
        Scene scene = new Scene(root);
        // Con esto añadimos el logo
        UIUtils.setAppIcon(stage);
        stage.setScene(scene);

    }

    public void show(){
        stage.show();
    }
    public Stage getStage() {
        return stage;
    }

    public TextField getTxtUsuer() {
        return txtUsuer;
    }

    public PasswordField getTxtPassword() {
        return txtPassword;
    }

    public Button getBtnEntrar() {
        return btnEntrar;
    }

    public Label getLblMensaje() {
        return lblMensaje;
    }
}
