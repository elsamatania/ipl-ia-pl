package metodos;

import agente.Problema;
import agente.Solucao;

public class PesquisaPorAprofundamentoProgressivo implements Pesquisa {

    public static final String NOME = "Aprofundamento progressivo";
    //private int limite=90;

    @Override
    public Solucao pesquisar(Problema problema) {
        PesquisaProfundidadeLimitada ps = new PesquisaProfundidadeLimitada();
        Solucao sol;
        for (int i = 0;; i++) {
            ps.setLimite(i);
            sol = ps.pesquisar(problema);
            if (sol != null) {
                return sol;
            }
        }
    }

    @Override
    public String toString() {
        return NOME;
    }
}
