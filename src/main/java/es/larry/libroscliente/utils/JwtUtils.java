package es.larry.libroscliente.utils;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

// Clase para decodificar el contenido del token.
public class JwtUtils {

    public static String decodePayload(String token) {
        String[] parts = token.split("\\.");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Token inválido");
        }

        String payload = parts[1];

        // Añadir padding si falta
        int padding = (4 - payload.length() % 4) % 4;
        payload += "=".repeat(padding);

        byte[] decodedBytes = Base64.getUrlDecoder().decode(payload);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    public static String decodeHeader(String token) {
        String[] parts = token.split("\\.");

        String header = parts[0];

        int padding = (4 - header.length() % 4) % 4;
        header += "=".repeat(padding);

        byte[] decodedBytes = Base64.getUrlDecoder().decode(header);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    //Extraemos el rol
    public static String getRole(String token){
        String payload = decodePayload(token);
        JSONObject json = new JSONObject(payload);
        return json.getString("role");
    }
}
