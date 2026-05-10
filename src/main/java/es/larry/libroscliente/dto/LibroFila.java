package es.larry.libroscliente.dto;

public class LibroFila {

    private int id;
    private String titulo;
    private String autor;
    private String editorial;
    private String estado;
    private String isbn;
    public LibroFila() {
    }

    public LibroFila(int id, String titulo, String autor, String editorial, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.estado = estado;
    }
    public LibroFila(String titulo, String autor, String editorial) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.estado = "DISPONIBLE";
        this.isbn = null;
    }
    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }
}
