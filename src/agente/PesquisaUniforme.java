package agente;

import java.util.LinkedList;
import java.util.List;

public class PesquisaUniforme<P extends Problema<E>, E extends Estado> extends MetodoPesquisaAGP<P,E>{

    private static final String NOME = "Uniforme";

    @Override
    public void inserirSucessores(No<E> noAExpandir, List<E> listaSucessores, LinkedList<No<E>> listaPorExpandir, P problema) {
        //todo
    }

    @Override
    public String toString() {
        return NOME;
    }


}
