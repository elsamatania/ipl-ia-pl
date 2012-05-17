package metodos;

import agente.Agente;
import agente.Estado;
import agente.No;
import java.util.List;
import java.util.PriorityQueue;

public class PesquisaSofrega extends MetodoPesquisaInformadoBFS<PriorityQueue<No>> {

    public static final String NOME = "Sofrega";

    public PesquisaSofrega(Agente agente) {
        super(agente);
        nosPorExpandir = new PriorityQueue<No>();
    }

    // f = h;
    @Override
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores) {
        for (Estado est : listaSucessores) {
            No no = new No(est, noAExpandir, noAExpandir.getG() + est.getOperador().getCusto(),
                    agente.getHeuristica().calcular(est));
            nosPorExpandir.add(no);
        }
    }

    @Override
    public String toString() {
        return NOME;
    }
}