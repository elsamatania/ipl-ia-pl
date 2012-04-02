package agente;

import java.util.LinkedList;
import java.util.List;

public class PesquisaLarguraPrimeiro<P extends Problema<E>, E extends Estado> extends MetodoPesquisaAGP<P,E> {

    private static final String NOME = "Largura primeiro";

    @Override
    public void inserirSucessores(No<E> noAExpandir, List<E> listaSucessores, LinkedList<No<E>> listaPorExpandir, P problema) {
        for (E est : listaSucessores) {
            No<E> no = new No<E>(est, noAExpandir, 0, 0);
            listaPorExpandir.add(no);
        }
    }

    @Override
    public String toString() {
        return NOME;
    }


}