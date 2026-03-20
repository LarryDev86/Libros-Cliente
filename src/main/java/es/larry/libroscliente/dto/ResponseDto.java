package es.larry.libroscliente.dto;

// Esta clase estructura lo que sera el cuerpo del la respuesta http del servcer hacia aqui
public class ResponseDto {

    private String token;

    public ResponseDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
