package metodos;

import agente.Estado;
import agente.No;
import agente.Problema;
import agente.Solucao;
import java.util.HashSet;
import java.util.List;
import utils.NodeCollection;


public abstract class MetodoPesquisaAGP<L extends NodeCollection> extends MetodoPesquisa<L> {

    protected HashSet<Estado> nosExpandidos = new HashSet<Estado>();
    
    protected int maxListaExpandidos;
    
    @Override
    public Solucao pesquisar(Problema problema) {
        this.problema = problema;
        return agp(problema);
    }

    public abstract void inserirSucessores(No noAExpandir, List<Estado> listaSucessores);

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

    private Solucao agp(Problema problema) {
        nosPorExpandir.clear();
        nosExpandidos.clear();
        numExpandidos = 0;
        numGerados = 0;
        maxListaPorExpandir = 0;

        No no;
        nosPorExpandir.add(new No(problema.getEstadoInicial()));
        numGerados++;
        
        while (!nosPorExpandir.isEmpty()) {
            maxListaPorExpandir = Math.max(maxListaPorExpandir, nosPorExpandir.size());
            no = nosPorExpandir.remove();

            nosExpandidos.add(no.getEstado());
            maxListaExpandidos = Math.max(maxListaExpandidos, nosExpandidos.size());

            if (problema.isObjetivoAtingido(no.getEstado())) {
                return new Solucao(problema, no);
            }
            
            List<Estado> sucessores = problema.aplicarOperadores(no.getEstado());
            numExpandidos++;
            numGerados += sucessores.size();
            inserirSucessores(no, sucessores);

        }
        return null;
    }
    
}
