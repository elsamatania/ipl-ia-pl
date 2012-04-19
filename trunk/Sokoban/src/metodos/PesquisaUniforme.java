package metodos;

import agente.Estado;
import agente.No;
import agente.Problema;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

public class PesquisaUniforme extends MetodoPesquisaAGP{

    public static final String NOME = "Uniforme";

    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores,
                                  LinkedList<No> listaPorExpandir, Problema problema) {

        //TODO
    }

    
    public String toString() {
        return NOME;
    }
}
