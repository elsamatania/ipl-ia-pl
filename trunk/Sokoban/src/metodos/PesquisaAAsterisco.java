package metodos;

import agente.Agente;
import agente.Estado;
import agente.No;
import java.util.List;
import utils.NodePriorityQueue;

public class PesquisaAAsterisco extends MetodoPesquisaInformadoBFS<NodePriorityQueue> {

    public static final String NOME = "A*";

    public PesquisaAAsterisco(Agente agente) {
        super(agente);
        nosPorExpandir = new NodePriorityQueue();
        completo = true; //por acaso depende da heurística
    }

    //f = g + h
    @Override
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores) {
        double custo;
        for (Estado est : listaSucessores) {
            if (!nosPorExpandir.contemEstado(est)) {
                custo = noAExpandir.getG() + est.getOperador().getCusto();
                No no = new No(est, noAExpandir, custo, custo + agente.getHeuristica().calcular(est));
                nosPorExpandir.add(no);
            }
        }
    }

    @Override
    public String toString() {
        return NOME;
    }
}