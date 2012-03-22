package agente;

import java.util.LinkedList;
import java.util.List;


public class PesquisaProfundidadeLimitada<P extends Problema<E>, E extends Estado> extends PesquisaProfundidadePrimeiro<P,E>{

    private static final String NOME = "Profundidade limitada";

    private int limite;

    public PesquisaProfundidadeLimitada() {
        this(28);
    }


    public PesquisaProfundidadeLimitada(int limite) {
        this.limite = limite;
    }


    public int getLimite(){
        return limite;
    }


    public void setLimite(int limite){
        this.limite = limite;
    }

    @Override
    public void inserirSucessores(No<E> noAExpandir, List<E> listaSucessores, LinkedList<No<E>> listaPorExpandir, P problema) {
        super.inserirSucessores(noAExpandir, listaSucessores, listaPorExpandir, problema);
    }

    @Override
    public String toString(){
        return NOME;
    }
}
