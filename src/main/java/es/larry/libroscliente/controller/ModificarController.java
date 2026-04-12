package es.larry.libroscliente.controller;

import es.larry.libroscliente.service.LoginService;
import es.larry.libroscliente.view.ModificarView;

public class ModificarController {

    private ModificarView modificarView;
    private LoginService service;

    public ModificarController(ModificarView modificarView) {
        this.modificarView = modificarView;
        this.service = new LoginService();
        initEvents();
    }
    // Accion cuando pulsamos el boton de logout invoca el metodo login y se ejecuta su contenido.
    private void initEvents(){
        modificarView.getBtnEntrar().setOnAction(e -> login());
    }
    private void login(){
        /*
        String nombre = loginView.getTxtUsuer().getText();
        String password = loginView.getTxtPassword().getText();

        try {
            String token = service.login(nombre, password);
            if (token != null && !token.isBlank()) {
                //Guardamos el token en memoria
                Sesion.setToken(token);
                //Aqui cerramos las dos ventanas.
                homeView.getStage().close();                       REPASAR BIEN Y ADAPTAR
                loginView.getStage().close();                       PORQUE ESTE NO APUNTA A UPDATE
                LogoutView lg = new LogoutView();                   APUNTA A COMPROBAR QUE EXISTA UN USUARIO EN BBDD
                lg.setRol(nombre);
                new LogoutController(lg,homeView);
                lg.show();
            } else {
                loginView.getLblMensaje().setText("Credenciales incorrectas");
            }

        } catch (Exception e) {
            loginView.getLblMensaje().setText("Error al conectar con el servidor");
        }

         */
    }
}
