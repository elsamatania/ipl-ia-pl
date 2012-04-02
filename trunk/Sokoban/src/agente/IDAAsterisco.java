package agente;

import java.util.LinkedList;
import java.util.List;


public class IDAAsterisco<P extends Problema<E>, E extends Estado> extends PesquisaAAsterisco<P,E> {
    private double limite;

    public IDAAsterisco(Agente agente){
        super(agente);
    }


    @Override
    public Solucao pesquisar(P problema){
        //TODO
        return null;
    }


    private Solucao agp(P problema) {
        //TODO
        return null;
    }


    private boolean isCiclo(No<E> no){
        //TODO
        return false;
    }

    @Override
    public void inserirSucessores(No<E> noAExpandir, List<E> listaSucessores, LinkedList<No<E>> listaPorExpandir, P problema) {
        //TODO
    }

    
    
}
