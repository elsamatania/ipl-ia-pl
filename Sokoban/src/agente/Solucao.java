package agente;

import java.util.LinkedList;
import java.util.List;

public class Solucao {

    private Problema problema;
    private int profundidade;
    private LinkedList<Operador> listaOperadores = new LinkedList<Operador>();

    public Solucao(Problema problema, No noObjectivo) {
        this.problema = problema;
        No no = noObjectivo;
        profundidade = no.getProfundidade();
        while (no.getAntecessor() != null) {
            listaOperadores.addFirst(no.getEstado().getOperador());
            no = no.getAntecessor();
        }
    }

    public double getCusto() {
        double custoTotal = 0;

        for (Operador o : listaOperadores) {
            custoTotal += o.getCusto();
        }

        return custoTotal;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public List<Operador> getOperadores() {
        return listaOperadores;
    }

    public void executar() {
        //TODO
    }
}