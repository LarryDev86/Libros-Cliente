package es.larry.libroscliente.service;

import es.larry.libroscliente.cliente.AuthHttpClient;
import es.larry.libroscliente.dto.RequestDtoRegistro;
import es.larry.libroscliente.dto.Usuario;

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

    public String registroUSer(String usuario, String nombreCompleto , String email , String password){
        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        return authHttpClient.registroUSer(usuario,nombreCompleto,email,password);
    }
    public int modificarUSer(String usuario, String nombreCompleto , String email , String password){
        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        return authHttpClient.modificarUSer(usuario,nombreCompleto,email,password);
    }

    public Usuario datosUsuario(){
        return authHttpClient.datosUsuario();
    }
}
