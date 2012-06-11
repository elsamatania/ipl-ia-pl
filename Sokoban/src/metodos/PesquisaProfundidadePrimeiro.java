package metodos;

import agente.Estado;
import agente.No;
import agente.Problema;
import agente.Solucao;
import java.util.List;
import utils.NodeLinkedList;

public class PesquisaProfundidadePrimeiro extends MetodoPesquisa<NodeLinkedList> {

    public static final String NOME = "Profundidade primeiro";

    public PesquisaProfundidadePrimeiro() {
        nosPorExpandir = new NodeLinkedList();
        completo = false;
    }

    @Override
    public Solucao pesquisar(Problema problema) {
        return agp(problema);
    }

    //AGP sem lista de nos expandidos
    private Solucao agp(Problema problema) {
        nosPorExpandir.clear();
        numExpandidos = 0;
        numGerados = 0;
        maxListaPorExpandir = 0;
        No no;
        nosPorExpandir.add(new No(problema.getEstadoInicial()));
        numGerados++;

        while (!nosPorExpandir.isEmpty()) {
            maxListaPorExpandir = Math.max(maxListaPorExpandir, nosPorExpandir.size());
            no = nosPorExpandir.removeFirst();

            if (problema.isObjetivoAtingido(no.getEstado())) {
                return new Solucao(problema, no);
            }

            List<Estado> sucessores = problema.aplicarOperadores(no.getEstado());
            numExpandidos++;
            numGerados += sucessores.size();
            inserirSucessores(no, sucessores);

        }
        return null;
    }

    private boolean isCiclo(No no) {
        No noAnterior = no.getAntecessor();

        while (noAnterior != null) {
            if (no.getEstado().equals(noAnterior.getEstado())) {
                return true;
            }
            noAnterior = noAnterior.getAntecessor();
        }

        return false;
    }

    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores) {

        for (Estado single : listaSucessores) {
            if (!nosPorExpandir.contemEstado(single)) {
                No novoNo = new No(single, noAExpandir, noAExpandir.getG() + single.getOperador().getCusto(), 0);
                if (!isCiclo(novoNo)) {
                    nosPorExpandir.addFirst(novoNo);
                }
            }
        }
    }

    @Override
    public String toString() {
        return NOME;
    }

}
