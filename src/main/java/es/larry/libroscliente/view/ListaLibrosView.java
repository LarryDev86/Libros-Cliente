package es.larry.libroscliente.view;

import es.larry.libroscliente.dto.LibroFila;
import es.larry.libroscliente.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class ListaLibrosView {

    private VBox root;
    private Stage stage;

    private Label lblTitulo;

    private TableView<LibroFila> tablaLibros;
    private TableColumn<LibroFila, Integer> colId;
    private TableColumn<LibroFila, String> colTitulo;
    private TableColumn<LibroFila, String> colAutor;
    private TableColumn<LibroFila, String> colGenero;
    private TableColumn<LibroFila, Boolean> colDisponi;

    public ListaLibrosView() {
        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("fondo");

        crearTitulo();
        crearTablaVacia();

        root.getChildren().addAll(
                crearTopBar(),
                lblTitulo,
                tablaLibros
        );

        VBox.setVgrow(tablaLibros, Priority.ALWAYS);

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
        lblTitulo = new Label("Listado de libros");
        lblTitulo.getStyleClass().add("titulo-seccion");
    }

    private void crearTablaVacia() {
        tablaLibros = new TableView<>();
        tablaLibros.getStyleClass().add("tabla-libros");
        tablaLibros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        colId = new TableColumn<>("ID");
        colTitulo = new TableColumn<>("Título");
        colAutor = new TableColumn<>("Autor");
        colGenero = new TableColumn<>("Categoria");
        colDisponi = new TableColumn<>("Disponibilidad");

        colId.setStyle("-fx-alignment: CENTER;");
        colTitulo.setStyle("-fx-alignment: CENTER;");
        colAutor.setStyle("-fx-alignment: CENTER;");
        colGenero.setStyle("-fx-alignment: CENTER;");
        colDisponi.setStyle("-fx-alignment: CENTER;");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colDisponi.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        colDisponi.setCellFactory(column -> new TableCell<LibroFila, Boolean>() {
            @Override
            protected void updateItem(Boolean disponible, boolean empty) {
                super.updateItem(disponible, empty);

                setAlignment(Pos.CENTER);

                if (empty || disponible == null) {
                    setText(null);
                    setStyle("-fx-alignment: CENTER;");
                } else if (disponible) {
                    setText("Disponible");
                    setStyle("-fx-alignment: CENTER; -fx-text-fill: green; -fx-font-weight: bold;");
                } else {
                    setText("No disponible");
                    setStyle("-fx-alignment: CENTER; -fx-text-fill: red; -fx-font-weight: bold;");
                }
            }
        });

        tablaLibros.setRowFactory(tv -> {
            TableRow<LibroFila> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    LibroFila libro = row.getItem();
                    mostrarDialogoLibro(libro);
                }
            });

            return row;
        });
        tablaLibros.getColumns().addAll(colId, colTitulo, colAutor, colGenero, colDisponi);
    }

    public void cargarLibros(List<LibroFila> listaLibros) {
        tablaLibros.getItems().clear();
        tablaLibros.getItems().addAll(listaLibros);
    }

    private void mostrarDialogoLibro(LibroFila libro) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.setTitle("Detalle del libro");
        alert.setHeaderText(libro.getTitulo());
        alert.setGraphic(null);

        String estado = libro.isDisponible() ? "Disponible" : "No disponible";

        alert.setContentText(
                "ID: " + libro.getId() + "\n" +
                        "Título: " + libro.getTitulo() + "\n" +
                        "Autor: " + libro.getAutor() + "\n" +
                        "Categoría: " + libro.getCategoria() + "\n" +
                        "Estado: " + estado
        );

        ButtonType botonAccion;
        ButtonType botonDevolver = null;

        if (libro.isDisponible()) {
            botonAccion = new ButtonType("Reservar");
        } else {
            botonAccion = new ButtonType("Entrar en lista de espera");
            botonDevolver = new ButtonType("Devolver libro");
        }

        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(botonAccion);

        if (botonDevolver != null) {
            alert.getButtonTypes().add(botonDevolver);
        }

        alert.getButtonTypes().add(botonCancelar);

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent()) {
            ButtonType response = resultado.get();

            if (response == botonAccion) {
                if (libro.isDisponible()) {
                    //reservarLibro(libro);
                    mostrarCazaPalabras(libro);
                } else {
                    apuntarListaEspera(libro);
                }
            } else if (botonDevolver != null && response == botonDevolver) {
                devolverLibro(libro);
            }
        }
    }

    private void reservarLibro(LibroFila libro) {
        Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
        confirmacion.initOwner(stage);
        confirmacion.setTitle("Reserva realizada");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("Has reservado el libro: " + libro.getTitulo());
        confirmacion.setGraphic(null);
        confirmacion.showAndWait();
    }

    private void apuntarListaEspera(LibroFila libro) {
        Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
        confirmacion.initOwner(stage);
        confirmacion.setTitle("Lista de espera");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("Te has añadido a la lista de espera de: " + libro.getTitulo());
        confirmacion.setGraphic(null);
        confirmacion.showAndWait();
    }

    private void devolverLibro(LibroFila libro) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle("Devolución");
        alert.setHeaderText(null);
        alert.setContentText("Has devuelto el libro: " + libro.getTitulo());
        alert.setGraphic(null);
        alert.showAndWait();
    }

    //ventana caza palabras
    private void mostrarCazaPalabras(LibroFila libro) {

        Dialog<String> dialog = new Dialog<>();
        dialog.initOwner(stage);
        dialog.setTitle("Caza-palabras");
        dialog.setHeaderText("Selecciona la palabra relacionada con el libro");

        dialog.setGraphic(null);

        // Simulación (luego esto vendrá del servidor)
        List<String> palabras = obtenerPalabrasDelServidor(libro);

        VBox contenido = new VBox(10);
        contenido.setPadding(new Insets(15));

        Label lblLibro = new Label("Libro: " + libro.getTitulo());

        ToggleGroup grupo = new ToggleGroup();

        VBox opcionesBox = new VBox(5);

        for (String palabra : palabras) {
            RadioButton rb = new RadioButton(palabra);
            rb.setToggleGroup(grupo);
            opcionesBox.getChildren().add(rb);
        }

        contenido.getChildren().addAll(lblLibro, opcionesBox);

        dialog.getDialogPane().setContent(contenido);

        ButtonType btnEnviar = new ButtonType("Enviar");
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(btnEnviar, btnCancelar);

        dialog.setResultConverter(button -> {
            if (button == btnEnviar) {
                RadioButton seleccionado = (RadioButton) grupo.getSelectedToggle();
                if (seleccionado != null) {
                    return seleccionado.getText();
                }
            }
            return null;
        });

        Optional<String> resultado = dialog.showAndWait();

        resultado.ifPresent(palabraElegida -> {
            validarRespuesta(libro, palabraElegida);
        });
    }
    // simulacion del server
    private List<String> obtenerPalabrasDelServidor(LibroFila libro) {
        // Simulación
        if (libro.getTitulo().toLowerCase().contains("castillo")) {
            return List.of("castillo", "avión", "pizza");
        }
        return List.of("libro", "coche", "mesa");
    }
    private void validarRespuesta(LibroFila libro, String palabra) {

        boolean acierto = palabraCorrecta(libro, palabra);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setGraphic(null);

        if (acierto) {
            alert.setTitle("Correcto");
            alert.setHeaderText(null);
            alert.setContentText("¡Correcto! Has ganado +10 puntos 🎉");
        } else {
            alert.setTitle("Incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("Fallaste 😢 No obtienes puntos extra");
        }

        alert.showAndWait();
    }
    //Logica de comprobacion
    private boolean palabraCorrecta(LibroFila libro, String palabra) {
        return libro.getTitulo().toLowerCase().contains(palabra.toLowerCase());
    }

    public Parent getView() {
        return root;
    }

    public Stage getStage() {
        return stage;
    }

    public TableView<LibroFila> getTablaLibros() {
        return tablaLibros;
    }

    public void show() {
        stage.show();
    }
}
