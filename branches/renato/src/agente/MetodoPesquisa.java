package agente;

public interface MetodoPesquisa<P extends Problema> {

    public Solucao pesquisar(P problema);

}