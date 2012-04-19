package metodos;

import agente.Agente;
import agente.Estado;
import agente.No;
import agente.Problema;
import java.util.List;
import java.util.LinkedList;


public class PesquisaEmFeixe  extends PesquisaAAsterisco{

    public static final String NOME = "Feixe";

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
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores,
                                  LinkedList<No> listaPorExpandir, Problema problema) {

        //TODO
    }


    @Override
    public String toString() {
        return NOME;
    }
}