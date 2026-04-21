package es.larry.libroscliente.controller;

import es.larry.libroscliente.dto.HistorialLibrosUser;
import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.view.ListaHistorialLibrosView;
import es.larry.libroscliente.view.ListarUserView;
import javafx.concurrent.Task;
import javafx.scene.control.Separator;

import java.util.List;

public class ListaUsersController {

    private final ListarUserView listarUserView;
    private final LoginService service;
    private final ListaHistorialLibrosView listaHistorialLibrosView;

    public ListaUsersController(ListarUserView listarUserView) {
        this.listarUserView = listarUserView;
        this.service = new LoginService();
        this.listaHistorialLibrosView = new ListaHistorialLibrosView();

        initUI();
        initData();
    }

    private void initUI() {
        Separator separator = new Separator();

        listarUserView.getRoot().getChildren().addAll(
                separator,
                listaHistorialLibrosView.getRoot()
        );
    }

    private void initData() {
        try {
            listarUserView.cargarLibros(service.datosUsuario());
        } catch (Exception e) {
            System.out.println("Error cargando usuario: " + e.getMessage());
        }

        Task<List<HistorialLibrosUser>> taskHistorial = new Task<>() {
            @Override
            protected List<HistorialLibrosUser> call() {
                return service.historialLibros();
            }
        };

        taskHistorial.setOnSucceeded(event -> {
            List<HistorialLibrosUser> historial = taskHistorial.getValue();
            listaHistorialLibrosView.cargarLibros(historial);
        });

        taskHistorial.setOnFailed(event -> {
            Throwable error = taskHistorial.getException();
            System.out.println("Error cargando historial: " + error.getMessage());
            error.printStackTrace();
        });

        Thread hilo = new Thread(taskHistorial);
        hilo.setDaemon(true);
        hilo.start();

        listarUserView.show();
    }
}