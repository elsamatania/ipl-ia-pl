package agente;

import java.util.HashMap;

/**
 *
 * <p>Title: Algoritmo Geral de Pesquisa</p>
 * <p>Description: Classe que representa um agente capaz de resolver problemas
 * utilizando metodos de pesquisa classica.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ESTG</p>
 * @author Carlos Grilo
 * @version 1.0
 */
public class Agente {

    /**
     * Mapa de hash que contem os metodos que o agente e capaz de utilizar.
     */
    private HashMap<String, MetodoPesquisa> metodosPesquisa = new HashMap<String, MetodoPesquisa>();


    /**
     * Metodo  de pesquisa actual.
     */
    private MetodoPesquisa metodoPesquisa;


    /**
     * Heuristica actual que o agente pode usar caso utilize um metodo informado.
     */
    private Heuristica heuristica;


    /**
     * Construtor que permite construir um agente.
     */
    public Agente() {
        //Define as capacidades do agente :-)
        MetodoPesquisa metodo = new PesquisaLarguraPrimeiro();
        metodosPesquisa.put(metodo.toString(), metodo);

        //Por omissao o nosso agente utiliza a pesquisa em largura
        metodoPesquisa = metodo;
        heuristica = null;

        metodo = new PesquisaProfundidadePrimeiro();
        metodosPesquisa.put(metodo.toString(), metodo);
        metodo = new PesquisaProfundidadeLimitada();
        metodosPesquisa.put(metodo.toString(), metodo);
        metodo = new PesquisaPorAprofundamentoProgressivo();
        metodosPesquisa.put(metodo.toString(), metodo);
        metodo = new PesquisaUniforme();
        metodosPesquisa.put(metodo.toString(), metodo);
        metodo = new PesquisaSofrega(this);
        metodosPesquisa.put(metodo.toString(), metodo);
        metodo = new PesquisaAAsterisco(this);
        metodosPesquisa.put(metodo.toString(), metodo);
        metodo = new PesquisaEmFeixe(this);
        metodosPesquisa.put(metodo.toString(), metodo);

    }


    /**
     * Devolve a heuristica actual.
     * @return Heuristica actual.
     */
    public Heuristica getHeuristica(){
        return heuristica;
    }


    /**
     * Define o metodo de pesquisa actual.
     * @param nome Nome do metodo a utilizar.
     */
    public void defineMetodoPesquisa(String nome){
        metodoPesquisa = metodosPesquisa.get(nome);
    }


    /**
     * Metodo que e chamado quando se pretende que o agente resolva um determinado problema.
     * @param problema Problema a resolver.
     * @param heuristica Heuristica a utilizar na resolucao do problema.
     * Se o metodo a utilizar for cego, este atributo e ignorado.
     * @return Devolve a solucao encontrada para o problema.
     */
    public Solucao resolveProblema(Problema problema, Heuristica heuristica){
        this.heuristica = heuristica;
        return metodoPesquisa.pesquisar(problema);
    }
}
