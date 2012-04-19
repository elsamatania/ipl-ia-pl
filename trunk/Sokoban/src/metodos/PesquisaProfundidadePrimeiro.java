package metodos;

import agente.Estado;
import agente.No;
import agente.Problema;
import agente.Solucao;
import java.util.List;
import java.util.LinkedList;


public class PesquisaProfundidadePrimeiro extends MetodoPesquisaAGP {

    public static final String NOME = "Profundidade primeiro";

    @Override
    public Solucao pesquisar(Problema problema) {
        return agp(problema);
    }


    //AGP sem lista de nos expandidos
    private Solucao agp(Problema problema) {
        //TODO
        return null;
    }


    private boolean isCiclo(No no){
        //TODO
        return false;
    }


    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores,
                                  LinkedList<No> listaPorExpandir, Problema problema) {

        //TODO
    }


    @Override
    public String toString() {
        return NOME;
    }
}
