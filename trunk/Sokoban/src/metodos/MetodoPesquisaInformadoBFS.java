package metodos;

import agente.Agente;
import agente.No;
import java.util.Queue;
import utils.NodeCollection;

public abstract class MetodoPesquisaInformadoBFS<L extends NodeCollection> extends MetodoPesquisaAGP<L>{
    protected Agente agente;


    public MetodoPesquisaInformadoBFS(Agente agente){
        this.agente = agente;
    }
}