package agente;

public abstract class MetodoPesquisaInformadoBFS<P extends Problema<E>, E extends Estado> extends MetodoPesquisaAGP<P,E>{
    protected Agente agente;

    public MetodoPesquisaInformadoBFS(Agente agente){
        this.agente = agente;
    }
}