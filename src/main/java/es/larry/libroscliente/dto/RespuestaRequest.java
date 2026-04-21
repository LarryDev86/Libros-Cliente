package es.larry.libroscliente.dto;

public class RespuestaRequest {

    private int preguntaId;
    private int opcioTriada;

    public RespuestaRequest() {
    }

    public RespuestaRequest(int preguntaId, int opcioTriada) {
        this.preguntaId = preguntaId;
        this.opcioTriada = opcioTriada;
    }

    public int getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(int preguntaId) {
        this.preguntaId = preguntaId;
    }

    public int getOpcioTriada() {
        return opcioTriada;
    }

    public void setOpcioTriada(int opcioTriada) {
        this.opcioTriada = opcioTriada;
    }
}