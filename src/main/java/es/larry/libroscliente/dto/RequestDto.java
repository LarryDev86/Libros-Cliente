package es.larry.libroscliente.dto;

// Clase que estructura el cuerpo del contenido de la peticion que enviaremos al server.
public class RequestDto {

    private String usuario;
    private String password;

    public RequestDto() {
    }

    public RequestDto(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
