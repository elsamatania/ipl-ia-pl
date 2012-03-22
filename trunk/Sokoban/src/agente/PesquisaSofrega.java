package agente;

import java.util.LinkedList;
import java.util.List;

public class PesquisaSofrega<P extends Problema<E>, E extends Estado> extends MetodoPesquisaInformadoBFS<P,E>{

    private static final String NOME = "Sofrega";

    public PesquisaSofrega(Agente agente){
        super(agente);
    }

    @Override
    public void inserirSucessores(No<E> noAExpandir, List<E> listaSucessores, LinkedList<No<E>> listaPorExpandir, P problema) {
        //todo
    }

  @Override
  public String toString() {
    return NOME;
  }


}