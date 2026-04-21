package es.larry.libroscliente.service;

import es.larry.libroscliente.cliente.AuthHttpClient;
import es.larry.libroscliente.dto.HistorialLibrosUser;
import es.larry.libroscliente.dto.LibroFila;
import es.larry.libroscliente.dto.Usuario;

import java.util.List;


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

    public int bajaUser(String token){
        return authHttpClient.bajaUser(token);
    }

    public int devolverLibro(int idLibro){
        return authHttpClient.devolverLibro(idLibro);
    }
    public int reservarLibro(int idLibro){
        return authHttpClient.reservarLibro(idLibro);
    }
    public Usuario datosUsuario(){
        return authHttpClient.datosUsuario();
    }

    public List<LibroFila> listarLibros(){
        return authHttpClient.listarLibros();
    }

    public List<HistorialLibrosUser>historialLibros(){
        return authHttpClient.historialLibros();
    }
    public List<HistorialLibrosUser>obtenerMiHistorialCompleto(){
        return authHttpClient.historialLibrosActivos();
    }

}
