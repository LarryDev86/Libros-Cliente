package es.larry.libroscliente.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.larry.libroscliente.dto.RequestDto;
import es.larry.libroscliente.dto.ResponseDto;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Clase para enviar y recibir peticiones Http con el servidor
public class AuthHttpClient {

    // Cambiar esta URL por la que apunte en el servidor
    private static final String LOGIN_URL = "http://localhost:8080/auth/login";
    private HttpClient client;
    private ObjectMapper mapper;

    public AuthHttpClient() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public String login(String usuario, String password) {
        try {
            RequestDto requestDto = new RequestDto(usuario, password);
            String json = mapper.writeValueAsString(requestDto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LOGIN_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            if (response.statusCode() == 200) {
                ResponseDto responseDto =
                        mapper.readValue(response.body(), ResponseDto.class);

                String token = responseDto.getToken();
            return token;
                /*
                // Comprobamos por consola la el contenido del token
                System.out.println("HEADER:");
                System.out.println(JwtUtils.decodeHeader(token));
                System.out.println("PAYLOAD:");
                System.out.println(JwtUtils.decodePayload(token));
                */
            }
            return null;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la autenticación", e);
        }
    }
}
