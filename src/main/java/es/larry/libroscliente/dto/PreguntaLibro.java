package es.larry.libroscliente.dto;

public class PreguntaLibro {

    private int preguntaId;
    private String textPregunta;
    private String opcio1;
    private String opcio2;
    private String opcio3;

    public PreguntaLibro() {
    }

    public int getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(int preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getTextPregunta() {
        return textPregunta;
    }

    public void setTextPregunta(String textPregunta) {
        this.textPregunta = textPregunta;
    }

    public String getOpcio1() {
        return opcio1;
    }

    public void setOpcio1(String opcio1) {
        this.opcio1 = opcio1;
    }

    public String getOpcio2() {
        return opcio2;
    }

    public void setOpcio2(String opcio2) {
        this.opcio2 = opcio2;
    }

    public String getOpcio3() {
        return opcio3;
    }

    public void setOpcio3(String opcio3) {
        this.opcio3 = opcio3;
    }
}