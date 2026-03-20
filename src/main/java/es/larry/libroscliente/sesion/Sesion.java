package es.larry.libroscliente.sesion;

//Clase para almacenar el token en memoria para la sesion.
public class Sesion {

    private static String token;

    public static String getToken(){
        return token;
    }
    public static void setToken(String token){
        Sesion.token = token;
    }
    public static void cleanToken(){
        token = null;
    }
}
