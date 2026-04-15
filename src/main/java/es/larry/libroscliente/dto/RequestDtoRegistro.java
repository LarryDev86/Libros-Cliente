package es.larry.libroscliente.dto;

public class RequestDtoRegistro {

    private String usuario;
    private String nombreCompleto;
    private String email;
    private String password;

    public RequestDtoRegistro(String usuario, String nombreCompleto, String email, String password) {
        this.usuario = usuario;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
