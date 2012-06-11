package metodos;

import agente.*;
import java.util.List;

public class IDAAsterisco extends PesquisaAAsterisco {

    public static final String NOME = "IDA*";
    private double limite;
    private double novoLimite;

    public IDAAsterisco(Agente agente) {
        super(agente);
    }

    @Override
    public Solucao pesquisar(Problema problema) {
        limite = agente.getHeuristica().calcular(problema.getEstadoInicial());
        //System.out.println("Limite inicial " + limite);
        numExpandidos = 0;
        numGerados = 0;

        Solucao sol;
        do {
            sol = agp(problema);
        } while (sol == null);

        return sol;
    }

    private Solucao agp(Problema problema) {
        nosPorExpandir.clear();
        numExpandidos = 0;
        numGerados = 0;
        maxListaPorExpandir = 0;
        novoLimite = Double.POSITIVE_INFINITY;
        nosPorExpandir.add(new No(problema.getEstadoInicial()));
        numGerados++;

        while (!nosPorExpandir.isEmpty()) {
            maxListaPorExpandir = Math.max(maxListaPorExpandir, nosPorExpandir.size());
            No noCorrente = nosPorExpandir.remove();

            if (problema.isObjetivoAtingido(noCorrente.getEstado())) {
                return new Solucao(problema, noCorrente);
            }

            List<Estado> sucessores = problema.aplicarOperadores(noCorrente.getEstado());
            numExpandidos++;
            numGerados += sucessores.size();
            inserirSucessores(noCorrente, sucessores);

            if (!sucessores.isEmpty()) {
                inserirSucessores(noCorrente, sucessores);
            }
        }

        limite = novoLimite;
        //System.out.println("Limite " + limite);
        return null;
    }

    private boolean isCiclo(No no) {
        No ant = no.getAntecessor();
        while (ant != null) {
            if (no.getEstado().equals(ant.getEstado())) {
                return true;
            }
            ant = ant.getAntecessor();
        }
        return false;
    }

    @Override
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores) {
        double g, f;
        for (Estado est : listaSucessores) {
            g = noAExpandir.getG() + est.getOperador().getCusto();
            if (!nosPorExpandir.contemEstado(est)) {
                f = g + agente.getHeuristica().calcular(est);
                if (f <= limite) {
                    No no = new No(est, noAExpandir, g, f);
                    if (!isCiclo(no)) {
                        nosPorExpandir.add(no);
                    }
                } else {
                    novoLimite = Math.min(novoLimite, f);
                }
            } else if (nosPorExpandir.getNo(est).getG() > g){
                nosPorExpandir.removeNo(est);
                nosPorExpandir.add(new No(est, noAExpandir, g, g + agente.getHeuristica().calcular(est)));
            }
        }
    }

    @Override
    public String toString() {
        return NOME;
    }
}
