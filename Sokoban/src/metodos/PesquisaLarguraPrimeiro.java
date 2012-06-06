package metodos;

import agente.Estado;
import agente.No;
import java.util.List;
import utils.NodeLinkedList;

public class PesquisaLarguraPrimeiro extends MetodoPesquisaAGP<NodeLinkedList> {

    public static final String NOME = "Largura primeiro";

    public PesquisaLarguraPrimeiro() {
        nosPorExpandir = new NodeLinkedList();
        completo = true;
    }

    @Override
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores) {
        for (Estado single : listaSucessores) {
            if (!nosPorExpandir.contemEstado(single) && !nosExpandidos.contains(single)) {
                No novoNo = new No(single, noAExpandir, noAExpandir.getG() + single.getOperador().getCusto(), 0);
                nosPorExpandir.addLast(novoNo);
            }
        }
    }

    @Override
    public String toString() {
        return NOME;
    }
}