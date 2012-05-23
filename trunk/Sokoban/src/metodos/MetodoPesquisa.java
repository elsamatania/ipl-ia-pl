package metodos;

import agente.No;
import agente.Problema;
import agente.Solucao;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public abstract class MetodoPesquisa<L extends Queue<No>> implements Pesquisa {

    protected Problema problema;
    protected L nosPorExpandir;
    protected boolean completo;

    public boolean isCompleto() {
        return completo;
    }
}