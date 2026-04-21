package es.larry.libroscliente.cliente;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.larry.libroscliente.dto.*;
import es.larry.libroscliente.sesion.Sesion;
import es.larry.libroscliente.utils.JwtUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


// Clase para enviar y recibir peticiones Http con el servidor
public class AuthHttpClient {

    // Cambiar esta URL por la que apunte en el servidor
    private static final String LOGIN_URL = "http://localhost:8080/auth/login";
    private static final String REGISTRO_URL = "http://localhost:8080/auth/register";
    private static final String MODIFICAR_URL = "http://localhost:8080/api/usuarios/user";
    private static final String LISTAR_URL = "http://localhost:8080/api/usuarios/user";
    private static final String LISTAR_LIBROS_URL = "http://localhost:8080/api/libros";
    private static final String DEVOLVER_LIBRO_URL = "http://localhost:8080/api/prestamos/";
    private static final String HISTORIAL = "http://localhost:8080/api/prestamos/mi-historial";
    private static final String PREGUNTA_RETO = "http://localhost:8080/api/prestamos/pregunta/";
    private static final String RESPONDER_RETO = "http://localhost:8080/api/prestamos/responder/";

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
                // Comprobamos por consola la el contenido del token
                System.out.println("HEADER:");
                System.out.println(JwtUtils.decodeHeader(token));
                System.out.println("PAYLOAD:");
                System.out.println(JwtUtils.decodePayload(token));
            return token;
            }
            return null;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la autenticación", e);
        }
    }

    public String registroUSer(String usuario, String nombreCompleto , String email , String password){
        try {
            RequestDtoRegistro requestDtoRegistro = new RequestDtoRegistro(usuario,nombreCompleto,email,password);
            String json = mapper.writeValueAsString(requestDtoRegistro);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(REGISTRO_URL))
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
                // Comprobamos por consola la el contenido del token
                System.out.println("HEADER:");
                System.out.println(JwtUtils.decodeHeader(token));
                System.out.println("PAYLOAD:");
                System.out.println(JwtUtils.decodePayload(token));
                return token;
            }
            return null;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la autenticación", e);
        }
    }

    public int modificarUSer(String usuario, String nombreCompleto , String email , String password){

        int respuesta = 0;

        try {
            String token = Sesion.getToken();
            RequestDtoRegistro requestDtoRegistro = new RequestDtoRegistro(usuario,nombreCompleto,email,password);
            String json = mapper.writeValueAsString(requestDtoRegistro);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MODIFICAR_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

        respuesta =  response.statusCode();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la autenticación", e);
        }
        return respuesta;
    }

    public Usuario datosUsuario(){
        Usuario usuarioRespuesta = null;
        try {
            String token = Sesion.getToken();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LISTAR_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            if (response.statusCode() == 200) {
                usuarioRespuesta = mapper.readValue(response.body(), Usuario.class);
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la autenticación", e);
        }
        return usuarioRespuesta;
    }
    public List<LibroFila> listarLibros(){
        List<LibroFila> listaLibros = new ArrayList<>();
        try {
            String token = Sesion.getToken();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LISTAR_LIBROS_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            if (response.statusCode() == 200) {
                listaLibros = mapper.readValue(response.body(), new TypeReference<List<LibroFila>>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                });
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la autenticación", e);
        }
        return listaLibros;
    }
    public int bajaUser(String token){
        int status = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LISTAR_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .DELETE()
                    .build();
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            status  = response.statusCode();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la autenticación", e);
        }
        return status;
    }
    public int devolverLibro(int idLibro){
        int status = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(DEVOLVER_LIBRO_URL+idLibro+"/devolver"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + Sesion.getToken())
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            status  = response.statusCode();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la autenticación", e);
        }
        return status;
    }

    public int reservarLibro(int idLibro){
        int status = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(DEVOLVER_LIBRO_URL+idLibro))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + Sesion.getToken())
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            status  = response.statusCode();
            System.out.println("Status: "+status);
            System.out.println("Body: "+response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la autenticación", e);
        }
        return status;
    }

    public List<HistorialLibrosUser> historialLibros() {

        List<HistorialLibrosUser> listaLibros = new ArrayList<>();

        try {
            String token = Sesion.getToken();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(HISTORIAL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() == 200) {
                MiHisotiralResponse historial = mapper.readValue(
                        response.body(),
                        MiHisotiralResponse.class
                );

                listaLibros = historial.getLibrosLeidos();
                //Extraemos los libros activos
                List<HistorialLibrosUser> listaActiva = historial.getPrestamosActivos();

            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la autenticación", e);
        }

        return listaLibros;
    }

    //Devolvemos lista de libros activos.
    public List<HistorialLibrosUser> historialLibrosActivos() {

        List<HistorialLibrosUser> listaActiva = new ArrayList<>();

        try {
            String token = Sesion.getToken();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(HISTORIAL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() == 200) {
                MiHisotiralResponse historial = mapper.readValue(
                        response.body(),
                        MiHisotiralResponse.class
                );

                listaActiva = historial.getPrestamosActivos();
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la autenticación", e);
        }

        return listaActiva;
    }

    public PreguntaLibro obtenerPregunta(int idLibro) throws Exception {

        String token = Sesion.getToken();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PREGUNTA_RETO+idLibro))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al obtener la pregunta: " + response.body());
        }

        return mapper.readValue(response.body(), PreguntaLibro.class);
    }
    public RespuestaResponse responderPregunta(int idLibro, int preguntaId, int opcioTriada) throws Exception {
        String token = Sesion.getToken();

        RespuestaRequest requestBody = new RespuestaRequest(preguntaId, opcioTriada);
        String json = mapper.writeValueAsString(requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RESPONDER_RETO + idLibro))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al responder la pregunta: " + response.body());
        }

        return mapper.readValue(response.body(), RespuestaResponse.class);
    }
}
