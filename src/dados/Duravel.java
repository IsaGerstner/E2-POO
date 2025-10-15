package dados;

public class Duravel extends Doacao {

    private TipoDuravel tipoDuravel;

    public Duravel(String descricao, double valor, int quantidade, TipoDuravel tipoDuravel){
        super ( descricao, valor, quantidade);
        this.tipoDuravel = tipoDuravel;
    }

    public TipoDuravel getTipoDuravel(){
        return this.tipoDuravel;
    }

    public String geraResumo(){
     return getDescricao() + "," + getValor() + "," + getQuantidade() + ","  + this.tipoDuravel + ",";
    }
    
}
