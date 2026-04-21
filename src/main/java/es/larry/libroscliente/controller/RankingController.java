package es.larry.libroscliente.controller;

import es.larry.libroscliente.dto.Ranking;
import es.larry.libroscliente.dto.Usuario;
import es.larry.libroscliente.view.RankingView;

import java.util.ArrayList;
import java.util.List;

public class RankingController {

    private RankingView rankingView;

    public RankingController(RankingView rankingView) {
        this.rankingView = rankingView;
        initiEvent();
    }

    private void initiEvent(){
        //Aqui tendremos que consultar la BBDD para extraer TODOS los usuarios y coger puntos y nombre
        List<Ranking> lRanking = new ArrayList<>();
        List<Usuario> listaUser = List.of(
                //new Usuario("Sandra","Martin marques","sandra@listilla.com",5)

        );

        //lRanking.sort((a,b) -> b.getPuntos() - a.getPuntos());
        rankingView.cargarTabla(lRanking);
        rankingView.show();
    }
}
