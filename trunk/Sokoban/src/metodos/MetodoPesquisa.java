package metodos;

import agente.No;
import agente.Problema;
import java.util.Queue;

public abstract class MetodoPesquisa<L extends Queue<No>> implements Pesquisa {

    protected Problema problema;
    protected L nosPorExpandir;
    protected boolean completo;
    protected int numExpandidos;
    protected int numGerados;
    protected int maxListaPorExpandir;

    @Override
    public boolean isCompleto() {
        return completo;
    }
    
    @Override
    public long getTamanhoMaximoConjuntoAExpandir() {
        return maxListaPorExpandir;
    }

    @Override
    public long getTotalNosExpandidos() {
        return numExpandidos;
    }

    @Override
    public long getTotalNosGerados() {
        return numGerados;
    }
}