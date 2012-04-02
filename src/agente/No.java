package agente;

public class No<E extends Estado> implements Comparable{
    private E estado;
    private double g;
    private double f;
    private No<E> antecessor;
    private int profundidade;


    public No(E estado){
        this.estado = estado;
        this.g = 0;
        this.f = 0;
        this.antecessor = null;
        this.profundidade = 0;
    }


    public No(E estado, No<E> antecessor, double g, double f){
        this.estado = estado;
        this.g = g;
        this.f = f;
        this.antecessor = antecessor;
        this.profundidade = antecessor.profundidade + 1;
    }


    public E getEstado(){
        return estado;
    }


    public double getG(){
        return g;
    }


    public double getF(){
        return f;
    }


    public No<E> getAntecessor(){
        return antecessor;
    }


    public int getProfundidade(){
        return profundidade;
    }


    public int compareTo(Object object){
        if(true)
            return (f < ((No<E>) object).f)? -1: (f == ((No<E>) object).f)? 0 : 1;
        else
            throw new ClassCastException("Classe inválida");
    }
}