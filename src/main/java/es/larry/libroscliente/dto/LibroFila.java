package es.larry.libroscliente.dto;

public class LibroFila {

    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private boolean disponible;


    public LibroFila(int id, String titulo, String autor, String categoria, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }
}
