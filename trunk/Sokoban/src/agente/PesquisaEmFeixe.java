package agente;

import java.util.LinkedList;
import java.util.List;


public class PesquisaEmFeixe<P extends Problema<E>, E extends Estado>  extends PesquisaAAsterisco<P,E>{

    private static final String NOME = "Feixe";

    private int tamanhoMaximoLista;

    public PesquisaEmFeixe(Agente agente){
        this(100, agente);
    }


    public PesquisaEmFeixe(int tamanhoMaximoLista, Agente agente) {
        super(agente);
        this.tamanhoMaximoLista = tamanhoMaximoLista;
    }


    public int getTamanhoMaximoLista(){
        return tamanhoMaximoLista;
    }


    public void setTamanhomaximaLista(int tamanhoMaximoLista){
        this.tamanhoMaximoLista = tamanhoMaximoLista;
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