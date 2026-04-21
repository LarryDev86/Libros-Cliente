package es.larry.libroscliente.controller;

import es.larry.libroscliente.dto.HistorialLibrosUser;
import es.larry.libroscliente.dto.LibroFila;
import es.larry.libroscliente.dto.PreguntaLibro;
import es.larry.libroscliente.dto.RespuestaResponse;
import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.view.ListaLibrosView;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListaLibrosController {

    private final ListaLibrosView listaLibrosView;
    private final LoginService service;
    private List<HistorialLibrosUser> listaActiva;

    public ListaLibrosController(ListaLibrosView listaLibrosView) {
        this.listaLibrosView = listaLibrosView;
        this.service = new LoginService();
        this.listaActiva = new ArrayList<>();

        initView();
        initEvents();
    }

    private void initView() {
        cargarLibros();
        cargarPrestamosActivos();
        listaLibrosView.show();
    }

    private void initEvents() {
        listaLibrosView.setOnReservar(this::reservarLibro);
        listaLibrosView.setOnListaEspera(this::apuntarListaEspera);
        listaLibrosView.setOnDevolver(this::devolverLibro);
    }

    private void cargarLibros() {
        try {
            List<LibroFila> lista = service.listarLibros();
            listaLibrosView.cargarLibros(lista);
        } catch (Exception e) {
            listaLibrosView.mostrarError("No se pudieron cargar los libros.");
        }
    }

    private void cargarPrestamosActivos() {
        try {
            listaActiva = service.obtenerMiHistorialCompleto();
            listaLibrosView.setListaActiva(listaActiva);
        } catch (Exception e) {
            listaActiva = new ArrayList<>();
            listaLibrosView.setListaActiva(listaActiva);
        }
    }

    private void refrescarLibros() {
        cargarLibros();
        cargarPrestamosActivos();
    }

    private void reservarLibro(LibroFila libro) {
        try {
            int status = service.reservarLibro(libro.getId());

            if (status != 200) {
                listaLibrosView.mostrarError("No se pudo reservar el libro.");
                return;
            }

            refrescarLibros();

            PreguntaLibro pregunta = service.obtenerPregunta(libro.getId());

            Optional<Integer> resultado = mostrarDialogoCazaPalabras(pregunta);

            if (resultado.isEmpty()) {
                listaLibrosView.mostrarMensajeReserva(libro);
                return;
            }

            int opcioTriada = resultado.get();

            RespuestaResponse respuesta = service.responderPregunta(
                    libro.getId(),
                    pregunta.getPreguntaId(),
                    opcioTriada
            );

            if (respuesta.getMissatge() != null && !respuesta.getMissatge().isBlank()) {
                listaLibrosView.mostrarMensajeInfo("Caza-palabras", respuesta.getMissatge());
            } else if (respuesta.isCorrecta()) {
                listaLibrosView.mostrarMensajeInfo(
                        "Caza-palabras",
                        "¡Has acertado! Has ganado +" + respuesta.getPuntosGanados() + " puntos extra."
                );
            } else {
                listaLibrosView.mostrarMensajeInfo(
                        "Caza-palabras",
                        "Has fallado. No obtienes puntos extra."
                );
            }

            listaLibrosView.mostrarMensajeReserva(libro);

        } catch (Exception e) {
            listaLibrosView.mostrarError("No se pudo completar la reserva.");
        }
    }

    private Optional<Integer> mostrarDialogoCazaPalabras(PreguntaLibro pregunta) {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.initOwner(listaLibrosView.getStage());
        dialog.setTitle("Caza-palabras");
        dialog.setHeaderText("Reto adicional");

        ButtonType botonAceptar = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(botonAceptar, botonCancelar);

        RadioButton rb1 = new RadioButton(pregunta.getOpcio1());
        RadioButton rb2 = new RadioButton(pregunta.getOpcio2());
        RadioButton rb3 = new RadioButton(pregunta.getOpcio3());

        ToggleGroup grupo = new ToggleGroup();
        rb1.setToggleGroup(grupo);
        rb2.setToggleGroup(grupo);
        rb3.setToggleGroup(grupo);

        rb1.setUserData(1);
        rb2.setUserData(2);
        rb3.setUserData(3);

        VBox contenido = new VBox(12);
        contenido.setStyle("-fx-padding: 10;");
        contenido.getChildren().addAll(rb1, rb2, rb3);

        dialog.getDialogPane().setContent(contenido);

        Node botonOk = dialog.getDialogPane().lookupButton(botonAceptar);
        botonOk.setDisable(true);

        grupo.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            botonOk.setDisable(newVal == null);
        });

        dialog.setResultConverter(buttonType -> {
            if (buttonType == botonAceptar && grupo.getSelectedToggle() != null) {
                return (Integer) grupo.getSelectedToggle().getUserData();
            }
            return null;
        });

        return dialog.showAndWait();
    }

    private void apuntarListaEspera(LibroFila libro) {
        listaLibrosView.mostrarMensajeListaEspera(libro);
    }

    private void devolverLibro(LibroFila libro) {
        try {
            int status = service.devolverLibro(libro.getId());

            if (status == 200) {
                refrescarLibros();
                listaLibrosView.mostrarMensajeDevolucion(libro);
            } else {
                listaLibrosView.mostrarError("No se pudo devolver el libro.");
            }
        } catch (Exception e) {
            listaLibrosView.mostrarError("Error al devolver el libro.");
        }
    }
}