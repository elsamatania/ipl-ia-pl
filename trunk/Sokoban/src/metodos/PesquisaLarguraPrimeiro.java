package metodos;

import agente.Estado;
import agente.No;
import agente.Problema;
import java.util.LinkedList;
import java.util.List;

public class PesquisaLarguraPrimeiro extends MetodoPesquisaAGP {

    public static final String NOME = "Largura primeiro";

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