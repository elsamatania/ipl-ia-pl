package agente;

import java.util.List;
import java.util.LinkedList;


public class PesquisaProfundidadePrimeiro<P extends Problema<E>, E extends Estado> extends MetodoPesquisaAGP<P,E> {

    public static final String NOME = "Profundidade primeiro";

    @Override
    public Solucao pesquisar(P problema) {
        return agp(problema);
    }


    //AGP sem lista de nos expandidos
    private Solucao agp(P problema) {
        //TODO
        return null;
    }


    private boolean isCiclo(No<E> no){
        //TODO
        return false;
    }

    @Override
    public String toString() {
        return NOME;
    }

    @Override
    public void inserirSucessores(No<E> noAExpandir, List<E> listaSucessores, LinkedList<No<E>> listaPorExpandir, P problema) {
        //todo
    }
}
