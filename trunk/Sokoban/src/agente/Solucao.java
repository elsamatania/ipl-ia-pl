package agente;

import java.util.List;
import java.util.LinkedList;

public class Solucao {
    private Problema problema;
    private LinkedList<Operador> listaOperadores = new LinkedList<Operador>();

    public Solucao(Problema problema, No noObjectivo){
        this.problema = problema;
        No no = noObjectivo;
        while(no.getAntecessor() != null){
            listaOperadores.addFirst(no.getEstado().getOperador());
            no = no.getAntecessor();
        }
    }

    public double custoSolucao(){
        //TODO
        return 0;
    }

    public List<Operador> getOperadores(){
        return listaOperadores;
    }

    public void executar(){
        //TODO
    }
}