package metodos;

import agente.Agente;
import agente.Estado;
import agente.No;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PesquisaEmFeixe extends PesquisaAAsterisco {

    public static final String NOME = "Feixe";
    private int tamanhoMaximoLista;

    public PesquisaEmFeixe(Agente agente) {
        this(20000, agente);
        completo = false;
    }

    public PesquisaEmFeixe(int tamanhoMaximoLista, Agente agente) {
        super(agente);
        this.tamanhoMaximoLista = tamanhoMaximoLista;
    }

    public int getTamanhoMaximoLista() {
        return tamanhoMaximoLista;
    }

    public void setTamanhomaximaLista(int tamanhoMaximoLista) {
        this.tamanhoMaximoLista = tamanhoMaximoLista;
    }

    @Override
    public void inserirSucessores(No noAExpandir, List<Estado> listaSucessores) {
        super.inserirSucessores(noAExpandir, listaSucessores);
        LinkedList<No> listaAux = new LinkedList<No>(nosPorExpandir);
        Collections.sort(listaAux);
        for (Iterator<No> it = listaAux.descendingIterator(); it.hasNext() && nosPorExpandir.size() > tamanhoMaximoLista;) {
            No no = it.next();
            nosPorExpandir.remove(no);
        }
          // ou então
//        while (nosPorExpandir.size() > tamanhoMaximoLista) {
//            nosPorExpandir.remove(Collections.max(nosPorExpandir));
//        }

        //System.out.println(nosPorExpandir.size());
    }

    @Override
    public String toString() {
        return NOME;
    }
}