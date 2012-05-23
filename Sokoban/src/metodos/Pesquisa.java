/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import agente.Problema;
import agente.Solucao;

/**
 *
 * @author Renato
 */
public interface Pesquisa {

    public Solucao pesquisar(Problema problema);
    
    public boolean isCompleto();

    public long getTotalNosExpandidos();

    public long getTotalNosGerados();

    public long getTamanhoMaximoConjuntoExpandidos();

    public long getTamanhoMaximoConjuntoAExpandir();
}
