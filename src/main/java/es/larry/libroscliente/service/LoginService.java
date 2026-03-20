package es.larry.libroscliente.service;

import es.larry.libroscliente.cliente.AuthHttpClient;

// Esta clase recoge la logica de negocio que se encarga de la peticiones al servidor
public class LoginService {

    private final AuthHttpClient authHttpClient;

    public LoginService() {
        this.authHttpClient = new AuthHttpClient();
    }

    public String login(String usuario, String password) {

        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        return authHttpClient.login(usuario, password);
    }
}
