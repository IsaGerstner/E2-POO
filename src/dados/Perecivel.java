package dados;

public class Perecivel extends Doacao {

    public static Object getTipo;
    private int validade;
    private TipoPerecivel tipoPerecivel;

    public Perecivel(String descricao, double valor, int quantidade, int validade, TipoPerecivel tipoPerecivel, Doador doador) {
        super(descricao, valor, quantidade, doador);
        this.validade = validade;
        this.tipoPerecivel = tipoPerecivel;
        
    }

    public TipoPerecivel getTipoPerecivel(){
        return this.tipoPerecivel;
    }

    public int getValidade(){
        return this.validade;
    }

    @Override 
    public String geraResumo(){
        return getDescricao() + "," + getValor() + "," + getQuantidade() +  "," + this.tipoPerecivel + "," + this.validade ;
    }
    
}
