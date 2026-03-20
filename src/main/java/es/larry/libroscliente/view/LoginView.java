package es.larry.libroscliente.view;

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

// Clase que define como sera la pantalla login
public class LoginView {

    private Stage stage;
    private TextField txtUsuer;
    private PasswordField txtPassword;
    private Button btnEntrar;
    private Label lblMensaje;

    // Aqui definimos como será la ventana de login del usuario
    public LoginView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Login user");
        stage.setWidth(380);
        stage.setHeight(300);
        Label lblUser = new Label("Usuario: ");
        lblUser.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtUsuer = new TextField();
        txtUsuer.setPrefWidth(280);
        Label lblPassword = new Label("Contraseña:");
        lblPassword.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        txtPassword = new PasswordField();
        txtPassword.setPrefWidth(280);
        btnEntrar = new Button("Entrar");
        btnEntrar.disableProperty().bind(
                txtUsuer.textProperty().isEmpty()
                        .or(txtPassword.textProperty().isEmpty())
        );
        lblMensaje = new Label();
        VBox root = new VBox(14,lblUser,txtUsuer,
                lblPassword,txtPassword
                ,btnEntrar,lblMensaje);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-background-color: #f4f4f4;");
        Scene scene = new Scene(root);
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

    public Button getBtnEntrar() {
        return btnEntrar;
    }

    public PasswordField getTxtPassword() {
        return txtPassword;
    }

    public TextField getTxtUsuer() {
        return txtUsuer;
    }
}
