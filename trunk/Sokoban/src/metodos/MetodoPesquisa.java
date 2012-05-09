package metodos;

import agente.No;
import agente.Problema;
import agente.Solucao;
import java.util.LinkedList;

public abstract class MetodoPesquisa implements Pesquisa {
    protected Problema problema;
    protected LinkedList<No> nosPorExpandir = new LinkedList<No>();

    

}