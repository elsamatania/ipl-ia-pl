package metodos;

import agente.Problema;
import agente.Solucao;

public class PesquisaPorAprofundamentoProgressivo implements Pesquisa {

    private long numExpandidosGlobal = 0;
    private long numGeradosGlobal = 0;
    private long tamanhoMaximoGlobal = 0;
    public static final String NOME = "Aprofundamento progressivo";

    @Override
    public Solucao pesquisar(Problema problema) {
        PesquisaProfundidadeLimitada ps = new PesquisaProfundidadeLimitada();
        Solucao sol;
        for (int i = 0;; i++) {
            ps.setLimite(i);
            sol = ps.pesquisar(problema);
            numExpandidosGlobal += ps.getTotalNosExpandidos();
            numGeradosGlobal += ps.getTotalNosGerados();
            tamanhoMaximoGlobal = Math.max(tamanhoMaximoGlobal, ps.getTamanhoMaximoConjuntoAExpandir());
            if (sol != null) {
                return sol;
            }
        }
    }

    @Override
    public String toString() {
        return NOME;
    }

    @Override
    public boolean isCompleto() {
        return true;
    }

    @Override
    public long getTotalNosExpandidos() {
        return numExpandidosGlobal;
    }

    @Override
    public long getTotalNosGerados() {
        return numGeradosGlobal;
    }

    @Override
    public long getTamanhoMaximoConjuntoAExpandir() {
        return tamanhoMaximoGlobal;
    }
}
