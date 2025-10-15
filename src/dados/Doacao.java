package dados;

public abstract class Doacao {
    private String descricao;
    private double valor;
    private int quantidade;
    private Doador doador;

    public Doador getDoador(){
        return null;
    }

    public abstract String geraResumo();

    public Doacao(String descricao, double valor, int quantidade){
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public String getDescricao (){
        return this.descricao;
    }

    public double getValor(){
        return this.valor;
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
    
}
