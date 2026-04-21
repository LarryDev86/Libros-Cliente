package es.larry.libroscliente.view;

import es.larry.libroscliente.dto.HistorialLibrosUser;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ListaLibrosView {

    private List<HistorialLibrosUser> listaActiva = new ArrayList<>();
    private VBox root;
    private Stage stage;

    private Label lblTitulo;
    private TableView<LibroFila> tablaLibros;
    private TableColumn<LibroFila, Integer> colId;
    private TableColumn<LibroFila, String> colTitulo;
    private TableColumn<LibroFila, String> colAutor;
    private TableColumn<LibroFila, String> colEditorial;
    private TableColumn<LibroFila, String> colEstado;

    private Consumer<LibroFila> onReservar;
    private Consumer<LibroFila> onListaEspera;
    private Consumer<LibroFila> onDevolver;

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
    private boolean esLibroActivoDelUsuario(int idLibro) {
        if (listaActiva == null || listaActiva.isEmpty()) {
            return false;
        }

        for (HistorialLibrosUser libroActivo : listaActiva) {
            if (libroActivo.getIdLibro() == idLibro) {
                return true;
            }
        }
        return false;
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
        colEditorial = new TableColumn<>("Editorial");
        colEstado = new TableColumn<>("Estado");

        colId.setStyle("-fx-alignment: CENTER;");
        colTitulo.setStyle("-fx-alignment: CENTER;");
        colAutor.setStyle("-fx-alignment: CENTER;");
        colEditorial.setStyle("-fx-alignment: CENTER;");
        colEstado.setStyle("-fx-alignment: CENTER;");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        colEstado.setCellFactory(column -> new TableCell<LibroFila, String>() {
            @Override
            protected void updateItem(String estado, boolean empty) {
                super.updateItem(estado, empty);

                setAlignment(Pos.CENTER);

                if (empty || estado == null) {
                    setText(null);
                    setStyle("-fx-alignment: CENTER;");
                } else if (estado.equalsIgnoreCase("DISPONIBLE")) {
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

        tablaLibros.getColumns().addAll(colId, colTitulo, colAutor, colEditorial, colEstado);
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

        String estado = libro.getEstado().equalsIgnoreCase("DISPONIBLE")
                ? "Disponible"
                : "No disponible";

        alert.setContentText(
                "ID: " + libro.getId() + "\n" +
                        "Título: " + libro.getTitulo() + "\n" +
                        "Autor: " + libro.getAutor() + "\n" +
                        "Editorial: " + libro.getEditorial() + "\n" +
                        "Estado: " + estado
        );

        ButtonType botonReservar = null;
        ButtonType botonListaEspera = null;
        ButtonType botonDevolver = null;
        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        boolean esActivo = esLibroActivoDelUsuario(libro.getId());

        if (libro.getEstado().equalsIgnoreCase("DISPONIBLE")) {
            botonReservar = new ButtonType("Reservar");
        } else {
            if (esActivo) {
                botonDevolver = new ButtonType("Devolver libro");
            } else {
                botonListaEspera = new ButtonType("Entrar en lista de espera");
            }
        }

        alert.getButtonTypes().clear();

        if (botonReservar != null) {
            alert.getButtonTypes().add(botonReservar);
        }
        if (botonListaEspera != null) {
            alert.getButtonTypes().add(botonListaEspera);
        }
        if (botonDevolver != null) {
            alert.getButtonTypes().add(botonDevolver);
        }

        alert.getButtonTypes().add(botonCancelar);

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent()) {
            ButtonType response = resultado.get();

            if (botonReservar != null && response == botonReservar) {
                if (onReservar != null) {
                    onReservar.accept(libro);
                }
            } else if (botonListaEspera != null && response == botonListaEspera) {
                if (onListaEspera != null) {
                    onListaEspera.accept(libro);
                }
            } else if (botonDevolver != null && response == botonDevolver) {
                if (onDevolver != null) {
                    onDevolver.accept(libro);
                }
            }
        }
    }

    public void mostrarMensajeReserva(LibroFila libro) {
        Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
        confirmacion.initOwner(stage);
        confirmacion.setTitle("Reserva realizada");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("Has reservado el libro: " + libro.getTitulo());
        confirmacion.setGraphic(null);
        confirmacion.showAndWait();
    }

    public void mostrarMensajeListaEspera(LibroFila libro) {
        Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
        confirmacion.initOwner(stage);
        confirmacion.setTitle("Lista de espera");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("Te has añadido a la lista de espera de: " + libro.getTitulo());
        confirmacion.setGraphic(null);
        confirmacion.showAndWait();
    }

    public void mostrarMensajeDevolucion(LibroFila libro) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle("Devolución");
        alert.setHeaderText(null);
        alert.setContentText("Has devuelto el libro: " + libro.getTitulo());
        alert.setGraphic(null);
        alert.showAndWait();
    }

    public void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.setGraphic(null);
        alert.showAndWait();
    }

    public void setOnReservar(Consumer<LibroFila> onReservar) {
        this.onReservar = onReservar;
    }

    public void setOnListaEspera(Consumer<LibroFila> onListaEspera) {
        this.onListaEspera = onListaEspera;
    }

    public void setOnDevolver(Consumer<LibroFila> onDevolver) {
        this.onDevolver = onDevolver;
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

    public void setListaActiva(List<HistorialLibrosUser> listaActiva) {
        this.listaActiva = listaActiva;
    }
}