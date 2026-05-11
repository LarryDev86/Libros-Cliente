package es.larry.libroscliente.cliente;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.larry.libroscliente.dto.*;
import es.larry.libroscliente.sesion.Sesion;
import es.larry.libroscliente.utils.JwtUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;


// Clase para enviar y recibir peticiones Http con el servidor
public class AuthHttpClient {

    // Cambiar esta URL por la que apunte en el servidor
    private static final String LOGIN_URL = "https://localhost:8443/auth/login";
    private static final String REGISTRO_URL = "https://localhost:8443/auth/register";
    private static final String MODIFICAR_URL = "https://localhost:8443/api/usuarios/user";
    private static final String LISTAR_URL = "https://localhost:8443/api/usuarios/user";
    private static final String LISTAR_LIBROS_URL = "https://localhost:8443/api/libros";
    private static final String DEVOLVER_LIBRO_URL = "https://localhost:8443/api/prestamos/";
    private static final String HISTORIAL = "https://localhost:8443/api/prestamos/mi-historial";
    private static final String PREGUNTA_RETO = "https://localhost:8443/api/prestamos/pregunta/";
    private static final String RESPONDER_RETO = "https://localhost:8443/api/prestamos/responder/";
    private static final String LISTAR_USUARIOS_ADMIN_URL = "https://localhost:8443/api/admin/usuarios";
    private static final String ELIMINAR_USUARIOS_ADMIN_URL = "https://localhost:8443/api/admin/usuarios/";
    private static final String INSERTAR_LIBRO = "https://localhost:8443/api/libros";
    private HttpClient client;
    private ObjectMapper mapper;

    public AuthHttpClient() {
        System.setProperty("jdk.internal.httpclient.disableHostnameVerification", "true");
        this.client = crearClienteHttpsInseguro();
        this.mapper = new ObjectMapper();
        // Ignora campos nuevos que vengan del server y que no existan en LibroFila
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private HttpClient crearClienteHttpsInseguro() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            return HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Error creando cliente HTTPS", e);
        }
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

            System.out.println("Status registro: " + response.statusCode());
            System.out.println("Body registro: " + response.body());

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
    public List<LibroFila> listarLibros() {
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

            System.out.println("Status libros: " + response.statusCode());
            System.out.println("Body libros: " + response.body());

            if (response.statusCode() == 200) {
                listaLibros = mapper.readValue(
                        response.body(),
                        new TypeReference<List<LibroFila>>() {}
                );
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al listar libros", e);
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

    public List<Usuario> listarUsuariosAdmin() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        try {
            String token = Sesion.getToken();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LISTAR_USUARIOS_ADMIN_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            System.out.println("Status usuarios: " + response.statusCode());
            System.out.println("Body usuarios: " + response.body());

            if (response.statusCode() == 200) {
                listaUsuarios = mapper.readValue(
                        response.body(),
                        new TypeReference<List<Usuario>>() {}
                );
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al listar usuarios admin", e);
        }

        return listaUsuarios;
    }

    public int eliminarUser(int id){
        int status  = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ELIMINAR_USUARIOS_ADMIN_URL+id))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + Sesion.getToken())
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

    public int restablecer(int id, String password){
        int status = 0;

        try {
            String token = Sesion.getToken();

            String json = """
                {
                    "novaContrasenya": "%s"
                }
                """.formatted(password);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://localhost:8443/api/admin/usuarios/" + id + "/contrasenya"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            status = response.statusCode();

            System.out.println("Status reset password: " + status);
            System.out.println("Body reset password: " + response.body());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al restablecer password", e);
        }

        return status;

    }

    public int insertarLibro(LibroFila libro){
        int status  = 0;
        try {

            String json = mapper.writeValueAsString(libro);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(INSERTAR_LIBRO))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + Sesion.getToken())
                    .POST(HttpRequest.BodyPublishers.ofString(json))
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

    public int borrarLibro(int idLibro){
        int status  = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(INSERTAR_LIBRO+"/"+idLibro))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + Sesion.getToken())
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
}
