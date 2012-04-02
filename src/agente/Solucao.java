package agente;

import java.util.LinkedList;
import java.util.List;

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
        return problema.getCustoSolucao(listaOperadores);
    }

    public List<Operador> getOperadores(){
        return listaOperadores;
    }

    public void executar(){
        //TODO
    }
}