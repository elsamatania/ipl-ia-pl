package agente;

import java.util.List;

/*
 * Classe que define um problema na sua máxima generalidade
 */
public abstract class Problema<E extends Estado> {
    
    //tem de ter um estado inicial
    protected E estadoInicial;
    
    //tem de ter uma lista de operadores possíveis
    protected List<Operador> listaOperadores;

    public Problema(E estadoInicial, List<Operador> listaOperadores) {
        this.estadoInicial = estadoInicial;
        this.listaOperadores = listaOperadores;
    }

    public E getEstadoInicial() {
        return estadoInicial;
    }
    
    //tem de ter um método para verificar se o objetivo foi atingido
    public abstract boolean isObjetivoAtingido(E e);
    
    //dado um estado, devolve a lista de todos os estados possíveis após a aplicação dos operadores
    public abstract List<E> aplicarOperadores(E e);

}