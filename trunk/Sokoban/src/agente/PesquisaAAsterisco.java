package agente;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

public class PesquisaAAsterisco<P extends Problema<E>, E extends Estado> extends MetodoPesquisaInformadoBFS<P,E> {

    public static final String NOME = "A*";

    public PesquisaAAsterisco(Agente agente){
        super(agente);
    }


    //f = g + h
    public void inserirSucessores(No<E> noAExpandir, List<E> listaSucessores,
                                  LinkedList<No<E>> listaPorExpandir, P problema) {
    
        //TODO
    }


    @Override
    public String toString() {
        return NOME;
    }
}