package agente;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public abstract class MetodoPesquisaAGP<P extends Problema<E>, E extends Estado> implements MetodoPesquisa<P>{

    @Override
    public Solucao pesquisar(P problema) {
        return agp(problema);
    }

    public abstract void inserirSucessores(No<E> noAExpandir, List<E> listaSucessores,  LinkedList<No<E>> listaPorExpandir, P problema);

    /*
      agp(problema, metodo de pesquisa) devolve uma solucao ou falha
      1. Criar e iniciar a lista NosPorExpandir com o estado inicial do problema.
      2. Criar a lista vazia NosExpandidos.
      3. Enquanto a lista NosPorExpandir não estiver vazia faz:
         4. Remover o primeiro no, n, da lista NosPorExpandir.
         5. Se n pertence a lista NosExpandidos, voltar ao passo 3.
         6. Introduzir n na lista NosExpandidos.
         7. Se n e um no objetivo, sai com sucesso, sendo a solucao obtida
            percorrendo o caminho desde n a n0 (os arcos sao criados no passo 9).
         8. Expandir n, gerando um conjunto S de sucessores;
         9. Introduzir cada um dos elementos de S na lista NosPorExpandir de acordo
            com o metodo de pesquisa. Criar arcos entre n e cada uma dos elementos de S.
        10. Voltar ao passo 3.
      */

    private Solucao agp(P problema) {
        LinkedList<No<E>> nosPorExpandir = new LinkedList<No<E>>();
        Set<No<E>> nosExpandidos = new HashSet<No<E>>();
        
        nosPorExpandir.add(new No<E>(problema.getEstadoInicial()));
        
        while(!nosPorExpandir.isEmpty()) {
            No<E> noCorrente = nosPorExpandir.removeFirst();
            if(nosExpandidos.contains(noCorrente)) {
                continue;
            }
            
            nosExpandidos.add(noCorrente);
            
            if(problema.isObjetivoAtingido(noCorrente.getEstado())) {
                return new Solucao(problema, noCorrente);
            }

            inserirSucessores(noCorrente, problema.aplicarOperadores(noCorrente.getEstado()), nosPorExpandir, problema);
        }
        return null;
    }
}
