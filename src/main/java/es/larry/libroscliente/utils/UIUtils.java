package es.larry.libroscliente.utils;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// Clase encargada del diseño del fondo de plantalla y el logo ( asi se puede reutilizar )
public class UIUtils {

    public static void applyMainStyle(Scene scene) {
        scene.getStylesheets().add(
                UIUtils.class.getResource("/css/stylo.css").toExternalForm()
        );
    }

    public static void setAppIcon(Stage stage) {
        stage.getIcons().add(
                new Image(UIUtils.class.getResourceAsStream("/images/Logo.jpg"))
        );
    }

    public static void setStyleMenu(Scene scene){
        scene.getStylesheets().add(
                UIUtils.class.getResource("/css/stylo.css").toExternalForm()
        );
    }
    public static void setStyleTablaLibros(Scene scene){
        scene.getStylesheets().add(
                UIUtils.class.getResource("/css/StyloTablaLibros.css").toExternalForm()
        );
    }
}
