package metodos;

import agente.Agente;
import agente.Estado;
import agente.No;
import agente.Problema;
import agente.Solucao;
import java.util.List;
import java.util.LinkedList;


public class IDAAsterisco extends PesquisaAAsterisco {
    private double limite;

    public IDAAsterisco(Agente agente){
        super(agente);
    }


    @Override
    public Solucao pesquisar(Problema problema){
        //TODO
        return null;
    }


    private Solucao agp(Problema problema) {
        //TODO
        return null;
    }


    private boolean isCiclo(No no){
        //TODO
        return false;
    }


    @Override
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores,
                                  LinkedList<No> listaPorExpandir, Problema problema) {
        //TODO
    }
}
