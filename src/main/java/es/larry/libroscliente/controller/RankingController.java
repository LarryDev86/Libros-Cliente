package es.larry.libroscliente.controller;

import es.larry.libroscliente.dto.Ranking;
import es.larry.libroscliente.dto.Usuario;
import es.larry.libroscliente.view.RankingView;

import java.util.ArrayList;
import java.util.List;

/*
* ESTA PANTALLA LA DEJAMOS ASI EN PROCCESO PORQUE EN EL SERVER NO HAN IMPLEMENTADO NADA
* PARA ELLO
* */
public class RankingController {

    private RankingView rankingView;
    private List<Usuario> listaUsers;

    public RankingController(RankingView rankingView, List<Usuario> lista) {
        this.rankingView = rankingView;
        this.listaUsers = lista;
        initiEvent();
        rankingView.getVolverBtn().setOnAction(e -> {
            rankingView.getStage().close();
        });
    }

    private void initiEvent(){
        //Aqui tendremos que consultar la BBDD para extraer TODOS los usuarios y coger puntos y nombre
        List<Ranking> lRanking = new ArrayList<>();
        for (Usuario usu  : listaUsers) {
            if(!usu.getRole().equalsIgnoreCase("ADMIN")){
                lRanking.add(new Ranking(usu.getUsuario(),usu.getPuntos()));
            }
        }
        //lRanking.sort((a,b) -> b.getPuntos() - a.getPuntos());
        rankingView.cargarTabla(lRanking);
        rankingView.show();
    }
}
