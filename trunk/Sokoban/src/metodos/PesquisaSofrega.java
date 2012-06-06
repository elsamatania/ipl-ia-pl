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
        double custo;
        for (Estado est : listaSucessores) {
            custo = noAExpandir.getG() + est.getOperador().getCusto();
            if(!nosPorExpandir.contemEstado(est)){
                if(!nosExpandidos.contains(est)){
                    No no = new No(est, noAExpandir, custo, agente.getHeuristica().calcular(est));
                    nosPorExpandir.add(no);
                }
            } else if (nosPorExpandir.getNo(est).getG() > custo){
                nosPorExpandir.removeNo(est);
                nosPorExpandir.add(new No(est, noAExpandir, custo, agente.getHeuristica().calcular(est)));
            }
        }
    }

    @Override
    public String toString() {
        return NOME;
    }
}