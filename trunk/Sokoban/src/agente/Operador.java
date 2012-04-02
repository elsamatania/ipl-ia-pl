package agente;

public abstract class Operador<E extends Estado> {
    private double custo;

    public Operador(double custo){
        this.custo = custo;
    }

    public double getCusto(){
        return custo;
    }
    
    public abstract void executar(E estado);
    public abstract boolean podeSerAplicado(E estado);
}