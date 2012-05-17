package metodos;

import agente.Estado;
import agente.No;
import agente.Problema;
import java.util.List;
import java.util.LinkedList;


public class PesquisaProfundidadeLimitada extends PesquisaProfundidadePrimeiro{

    public static final String NOME = "Profundidade limitada";

    private int limite;

    public PesquisaProfundidadeLimitada() {
        this(100);
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
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores) {

         if (noAExpandir.getProfundidade() < limite){
            super.inserirSucessores(noAExpandir, listaSucessores);
        }
    }


    @Override
    public String toString(){
        return NOME;
    }
}
