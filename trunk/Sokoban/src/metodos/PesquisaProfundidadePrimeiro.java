package metodos;

import agente.Estado;
import agente.No;
import agente.Problema;
import agente.Solucao;
import java.util.List;
import java.util.LinkedList;


public class PesquisaProfundidadePrimeiro extends MetodoPesquisa {

    public static final String NOME = "Profundidade primeiro";

    @Override
    public Solucao pesquisar(Problema problema) {
        return agp(problema);
    }


    //AGP sem lista de nos expandidos
    private Solucao agp(Problema problema) {
        No no;
        nosPorExpandir.add(new No(problema.getEstadoInicial()));
        while (!nosPorExpandir.isEmpty()) {
            no = nosPorExpandir.removeFirst();

            if (problema.isObjetivoAtingido(no.getEstado())) {
                return new Solucao(problema, no);
            }

            inserirSucessores(no, problema.aplicarOperadores(no.getEstado()));

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

            No novoNo = new No(single, noAExpandir, noAExpandir.getG() + single.getOperador().getCusto(), 0);
            if (!isCiclo(novoNo)) {
                nosPorExpandir.addFirst(novoNo);
            }
        }
    }


    @Override
    public String toString() {
        return NOME;
    }
}
