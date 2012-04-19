package metodos;

import agente.Agente;
import agente.Estado;
import agente.No;
import agente.Problema;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

public class PesquisaAAsterisco extends MetodoPesquisaInformadoBFS {

    public static final String NOME = "A*";

    public PesquisaAAsterisco(Agente agente){
        super(agente);
    }


    //f = g + h
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores,
                                  LinkedList<No> listaPorExpandir, Problema problema) {
    
        //TODO
    }


    @Override
    public String toString() {
        return NOME;
    }
}