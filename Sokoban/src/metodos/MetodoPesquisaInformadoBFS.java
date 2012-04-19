package metodos;

import agente.Agente;

public abstract class MetodoPesquisaInformadoBFS extends MetodoPesquisaAGP{
    protected Agente agente;


    public MetodoPesquisaInformadoBFS(Agente agente){
        this.agente = agente;
    }
}