package metodos;

import agente.Agente;
import agente.Estado;
import agente.No;
import java.util.List;
import utils.NodePriorityQueue;

public class PesquisaSofrega extends MetodoPesquisaInformadoBFS<NodePriorityQueue> {

    public static final String NOME = "Sôfrega";

    public PesquisaSofrega(Agente agente) {
        super(agente);
        nosPorExpandir = new NodePriorityQueue();
        completo = true;
    }

    // f = h;
    @Override
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores) {
        for (Estado est : listaSucessores) {
            if (!nosPorExpandir.contemEstado(est)) {
                No no = new No(est, noAExpandir, noAExpandir.getG() + est.getOperador().getCusto(),
                        agente.getHeuristica().calcular(est));
                nosPorExpandir.add(no);
            }
        }
    }

    @Override
    public String toString() {
        return NOME;
    }
}