package metodos;

import agente.Agente;
import agente.No;
import java.util.Queue;

public abstract class MetodoPesquisaInformadoBFS<L extends Queue<No>> extends MetodoPesquisaAGP<L>{
    protected Agente agente;


    public MetodoPesquisaInformadoBFS(Agente agente){
        this.agente = agente;
    }
}