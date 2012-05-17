package metodos;

import agente.Estado;
import agente.No;
import java.util.List;
import java.util.PriorityQueue;

public class PesquisaUniforme extends MetodoPesquisaAGP<PriorityQueue<No>> {

    public PesquisaUniforme() {
        nosPorExpandir = new PriorityQueue<No>();
    }
    public static final String NOME = "Uniforme";

    @Override
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores) {
        double custo;
        for (Estado est : listaSucessores) {
            custo = noAExpandir.getG() + est.getOperador().getCusto();
            No no = new No(est, noAExpandir, custo, custo);
            nosPorExpandir.add(no);
        }
    }

    @Override
    public String toString() {
        return NOME;
    }
}
