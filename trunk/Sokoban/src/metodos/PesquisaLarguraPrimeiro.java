package metodos;

import agente.Estado;
import agente.No;
import java.util.LinkedList;
import java.util.List;

public class PesquisaLarguraPrimeiro extends MetodoPesquisaAGP<LinkedList<No>> {

    public static final String NOME = "Largura primeiro";

    public PesquisaLarguraPrimeiro() {
        nosPorExpandir = new LinkedList<No>();
    }

    @Override
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores) {
        for (Estado single : listaSucessores) {
            No novoNo = new No(single, noAExpandir, noAExpandir.getG() + single.getOperador().getCusto(), 0);
            nosPorExpandir.addLast(novoNo);
        }
    }


    @Override
    public String toString() {
        return NOME;
    }

}